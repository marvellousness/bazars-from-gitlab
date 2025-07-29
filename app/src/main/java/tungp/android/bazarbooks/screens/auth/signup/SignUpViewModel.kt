package tungp.android.bazarbooks.screens.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tungp.android.bazarbooks.util.CredentialsStorage
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.usecase.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val credentialsStorage: CredentialsStorage,
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    private val _eventChannel = Channel<SignUpEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChanged -> {
                val email = event.email
                val emailError = validateEmail(email)
                _state.update { it.copy(email = email, emailError = emailError) }
            }
            is SignUpEvent.PasswordChanged -> {
                val password = event.password
                val passwordError = validatePassword(password)
                _state.update { it.copy(password = password, passwordError = passwordError) }
            }
            is SignUpEvent.AddressChanged -> {
                _state.update { it.copy(address = event.address) }
            }
            is SignUpEvent.PhoneChanged -> {
                _state.update { it.copy(phone = event.phone) }
            }
            is SignUpEvent.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val currentState = _state.value
            val emailError = validateEmail(currentState.email ?: "")
            val passwordError = validatePassword(currentState.password ?: "")

            if (emailError != null || passwordError != null) {
                _state.update {
                    it.copy(
                        emailError = emailError,
                        passwordError = passwordError,
                        isLoading = false
                    )
                }
                return@launch
            }

            signUpUseCase(
                SignUpUseCase.Params(
                    currentState.email ?: "",
                    currentState.password ?: "",
                    currentState.address ?: "",
                    currentState.phone ?: ""
                )
            ).collect { result ->
                when (result) {
                    is BazaResult.Loading -> _state.update { it.copy(isLoading = true) }
                    is BazaResult.Success -> {
                        credentialsStorage.saveCredentials(currentState.email, currentState.password)
                        _state.update { it.copy(isSignUpSuccess = true, isLoading = false) }
                    }
                    is BazaResult.Error -> _state.update { it.copy(error = result.message ?: result.exception.message ?: "Unknown error", isLoading = false) }
                }
            }
        }
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email format"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 6 -> "Password must be at least 6 characters long"
            else -> null
        }
    }
}

// State and Event classes should be created in the same or separate files as needed. 