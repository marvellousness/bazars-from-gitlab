package tungp.android.bazarbooks.data.remote.model.dto

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.User

@Serializable
data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
) {
    fun asDomainModel(): User {
        return User(
            id = id,
            username = username,
            email = email,
            password = password,
            phone = phone,
            address = address
        )
    }
}