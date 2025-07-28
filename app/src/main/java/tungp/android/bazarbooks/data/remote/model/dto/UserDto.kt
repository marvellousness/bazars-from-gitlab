package tungp.android.bazarbooks.data.remote.model.dto

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.User

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    val address: String,
    val phone: String,
) {
    fun asDomainModel(): User {
        return User(
            id = id,
            email = email,
            address = address,
            phone = phone
        )
    }
}