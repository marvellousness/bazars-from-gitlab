package tungp.android.bazarbooks.data.remote.mapper

import tungp.android.bazarbooks.data.remote.model.dto.UserDto
import tungp.android.bazarbooks.domain.model.User

fun UserDto.toUser(): User {
    return User(
        id = id,
        email = email,
        address = address,
        phone = phone
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        address = address,
        phone = phone
    )
}
