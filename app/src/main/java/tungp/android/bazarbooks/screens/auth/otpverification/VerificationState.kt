package tungp.android.bazarbooks.screens.auth.otpverification

data class VerificationState(
    val isLoading: Boolean = false,
    val isCodeSent: Boolean = false,
    val error: String? = null,
    val emailOrPhone: String? = null,
    val username: String? = null,
    val password: String? = null,
    val phone: String? = null,
    val code: List<Char?> = List(4) { null }
)