package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val username: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String
)
