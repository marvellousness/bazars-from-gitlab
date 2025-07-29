package tungp.android.bazarbooks.screens.auth.signup

sealed class SignUpEvent {
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class AddressChanged(val address: String) : SignUpEvent()
    data class PhoneChanged(val phone: String) : SignUpEvent()
    object SignUp : SignUpEvent()
} 