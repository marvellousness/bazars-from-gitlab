package tungp.android.bazarbooks.screens.auth.signin

data class SignInState(
    val email: String? = null,
    val password: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isSignInSuccess: Boolean = false,
    val error: String? = null
)