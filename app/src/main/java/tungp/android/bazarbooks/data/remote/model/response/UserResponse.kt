package tungp.android.bazarbooks.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.data.local.dao.UserDao
import tungp.android.bazarbooks.data.remote.model.dto.UserDto
import tungp.android.bazarbooks.domain.model.Book

@Serializable
data class UserResponse(
    @SerialName("user")
    val user: UserDto
)