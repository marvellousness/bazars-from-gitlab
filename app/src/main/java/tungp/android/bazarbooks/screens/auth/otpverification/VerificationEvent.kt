package tungp.android.bazarbooks.screens.auth.otpverification

sealed class VerificationEvent {
    data class CodeChanged(val code: List<Char?>) : VerificationEvent()
    object VerifyCode : VerificationEvent()
    object ResendCode : VerificationEvent()
    object ResetState : VerificationEvent()
}