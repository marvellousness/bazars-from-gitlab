package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    val address: String,
    val phone: String
)
