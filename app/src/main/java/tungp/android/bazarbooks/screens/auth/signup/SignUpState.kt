package tungp.android.bazarbooks.screens.auth.signup

import tungp.android.bazarbooks.domain.model.User

data class SignUpState(
    val user: User? = null,
    val email: String? = null,
    val password: String? = null,
    val username: String? = null,
    val phone: String? = null,
    val usernameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isSignUpSuccess: Boolean = false,
    val error: String? = null
) 