package tungp.android.bazarbooks.screens.auth.signin

sealed class SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent()
    data class PasswordChanged(val password: String) : SignInEvent()
    object SignInClicked : SignInEvent()
    object GoogleSignInClicked : SignInEvent()
    object AppleSignInClicked : SignInEvent()
}