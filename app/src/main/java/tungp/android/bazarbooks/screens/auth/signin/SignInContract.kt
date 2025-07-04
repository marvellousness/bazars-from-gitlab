package tungp.android.bazarbooks.screens.auth.signin


data class SignInState(
    val email: String? = null,
    val password: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
)

sealed class SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent()
    data class PasswordChanged(val password: String) : SignInEvent()
    object SignInClicked : SignInEvent()
    object GoogleSignInClicked : SignInEvent()
    object AppleSignInClicked : SignInEvent()
    object ForgotPasswordClicked : SignInEvent()
}