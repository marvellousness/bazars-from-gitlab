package tungp.android.bazarbooks.data.remote.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    val address: String,
    val phone: String
)