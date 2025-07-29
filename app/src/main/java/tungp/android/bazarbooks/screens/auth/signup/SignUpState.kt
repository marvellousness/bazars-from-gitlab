package tungp.android.bazarbooks.screens.auth.signup

data class SignUpState(
    val email: String? = null,
    val password: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isSignUpSuccess: Boolean = false,
    val error: String? = null
) 