package tungp.android.bazarbooks.screens.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.usecase.SignUpUseCase
import tungp.android.bazarbooks.util.CredentialsStorage
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val credentialsStorage: CredentialsStorage,
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {
    private val _signUpstate = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpstate.asStateFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChanged -> {
                val email = event.email
                val emailError = null
                _signUpstate.update { it.copy(email = email, emailError = emailError) }
            }

            is SignUpEvent.PasswordChanged -> {
                val password = event.password
                val passwordError = null
                _signUpstate.update { it.copy(password = password, passwordError = passwordError) }
            }

            is SignUpEvent.UsernameChanged -> {
                val username = event.username
                val usernameError = null
                _signUpstate.update { it.copy(username = event.username, usernameError = usernameError) }
            }

            is SignUpEvent.RegisterNewUser -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            _signUpstate.update { it.copy(isLoading = true, error = null) }
            val currentState = _signUpstate.value
            val emailError = validateEmail(currentState.email ?: "")
            val passwordError = validatePassword(currentState.password ?: "")

            signUpUseCase(
                SignUpUseCase.Params(
                    currentState.email ?: "",
                    currentState.password ?: "",
                    currentState.username ?: ""
                )
            ).collect { result ->
                when (result) {
                    is BazaResult.Loading -> _signUpstate.update { it.copy(isLoading = true) }
                    is BazaResult.Success -> {
                        _signUpstate.update {currentState ->
                            currentState.copy(
                                isSignUpSuccess = true,
                                isLoading = false,
                                user = result.data
                            )
                        }
                    }

                    is BazaResult.Error -> _signUpstate.update {
                        it.copy(
                            error = result.message ?: result.exception.message ?: "Unknown error",
                            isLoading = false
                        )
                    }
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

    private fun validateUsername(username: String): String? {
        return when {
            username.isBlank() -> "Username cannot be empty"
            else -> null
        }
    }
}

// State and Event classes should be created in the same or separate files as needed. 