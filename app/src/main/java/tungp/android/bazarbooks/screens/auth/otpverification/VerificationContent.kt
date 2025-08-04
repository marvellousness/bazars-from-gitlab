package tungp.android.bazarbooks.screens.auth.otpverification

import kotlinx.serialization.Serializable

@Serializable
data class VerificationContent(
    val title: String,
    val subtitle: String,
    val code_placeholder: String,
    val verify_button: String,
    val resend_button: String
)