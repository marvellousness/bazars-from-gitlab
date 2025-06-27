package tungp.android.bazarbooks.screens.auth.signin

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : MviViewModel<SignInState, SignInEvent>() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    override fun onTriggerEvent(eventType: SignInEvent) {
        when (eventType) {
            is SignInEvent.SignInClicked -> {
                signIn(eventType.email, eventType.password)
            }

            is SignInEvent.EmailChanged -> {
                _state.update { currentState ->
                    currentState.copy(email = eventType.email)
                }
            }

            is SignInEvent.PasswordChanged -> {
                _state.update { currentState ->
                    currentState.copy(password = eventType.password)
                }
            }

            is SignInEvent.ValidateEmail -> {
                _state.update { currentState ->
                    currentState.copy(emailError = validateEmail(currentState.email))
                }
            }

            is SignInEvent.ValidatePassword -> {
                _state.update { currentState ->
                    currentState.copy(passwordError = validatePassword(currentState.password))
                }
            }

            else -> {}
        }
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"
            email.length < 2 -> "Email must be at least 3 characters long"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 2 -> "Password must be at least 1 characters long"
            else -> null
        }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn: email: $email, password: $password")

    }

    companion object {
        const val TAG = "~~~SignInViewModel"
    }

}