package tungp.android.bazarbooks.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val email: String,
    val password: String,
    val username: String
) 