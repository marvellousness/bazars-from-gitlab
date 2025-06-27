package tungp.android.bazarbooks.screens.auth.signin


data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
)

sealed class SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent()
    object ValidateEmail : SignInEvent()
    data class PasswordChanged(val password: String) : SignInEvent()
    object ValidatePassword : SignInEvent()
    data class SignInClicked(val email: String, val password: String) : SignInEvent()
    object GoogleSignInClicked : SignInEvent()
    object AppleSignInClicked : SignInEvent()
    object ForgotPasswordClicked : SignInEvent()
}