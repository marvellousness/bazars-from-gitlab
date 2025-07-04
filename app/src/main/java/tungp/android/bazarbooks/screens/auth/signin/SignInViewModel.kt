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
                signIn()
            }

            is SignInEvent.EmailChanged -> {
                val email = eventType.email
                val emailError = validateEmail(email)
                _state.update { currentState ->
                    currentState.copy(email = email, emailError = emailError)
                }
            }

            is SignInEvent.PasswordChanged -> {
                val password = eventType.password
                val passwordError = validatePassword(password)
                _state.update { currentState ->
                    currentState.copy(password = password, passwordError = passwordError)
                }
            }

            else -> {}
        }
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"
            email.length < 3 -> "Email must be at least 3 characters long"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 3 -> "Password must be at least 3 characters long"
            else -> null
        }
    }

    private fun signIn() {
        Log.d(TAG, "signIn: email: ${state.value.email}, password: ${state.value.password}")
    }

    fun validateForm(): Boolean {
        val currentState = state.value
        return currentState.emailError == null &&
               currentState.passwordError == null &&
               !currentState.email.isNullOrBlank() &&
               !currentState.password.isNullOrBlank()
    }

    companion object {
        const val TAG = "SignInViewModel"
    }

}