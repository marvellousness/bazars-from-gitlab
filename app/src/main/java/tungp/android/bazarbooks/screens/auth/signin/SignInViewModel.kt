package tungp.android.bazarbooks.screens.auth.signin

import android.util.Log
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
import javax.inject.Inject
import tungp.android.bazarbooks.domain.repository.AuthRepository
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.usecase.SignInUseCase

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val credentialsStorage: CredentialsStorage,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    private val _eventChannel = Channel<SignInEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                val email = event.email
                val emailError = validateEmail(email)
                _state.update { currentState ->
                    currentState.copy(email = email, emailError = emailError)
                }
            }

            is SignInEvent.PasswordChanged -> {
                val password = event.password
                val passwordError = validatePassword(password)
                _state.update { currentState ->
                    currentState.copy(password = password, passwordError = passwordError)
                }
            }

            is SignInEvent.SignIn -> {
                signIn()
            }
            is SignInEvent.GoogleSignInClicked -> {
                // Handle Google Sign-in
            }
            is SignInEvent.AppleSignInClicked -> {
                // Handle Apple Sign-in
            }
            is SignInEvent.ForgotPasswordClicked -> {
                // Handle Forgot Password
            }
        }
    }

    private fun signIn() {
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

            val TAG = "SignInViewModel"

            signInUseCase(SignInUseCase.Params(currentState.email ?: "", currentState.password ?: "")).collect { result ->

                Log.d(TAG, "signIn: result=${result}")

                when (result) {
                    is BazaResult.Loading -> _state.update { it.copy(isLoading = true) }
                    is BazaResult.Success -> {
                        credentialsStorage.saveCredentials(currentState.email, currentState.password)
                        _state.update { it.copy(isSignInSuccess = true, isLoading = false) }
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