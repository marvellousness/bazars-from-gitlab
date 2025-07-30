package tungp.android.bazarbooks.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val email: String,
    val password: String
) 