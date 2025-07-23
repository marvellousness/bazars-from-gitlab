package tungp.android.bazarbooks.data.remote.model.dto

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Author

@Serializable
data class AuthorDto(
    val id: String,
    val title: String,
    val cover: String,
    val authorName: String,
    val category: String,
    val rating: Int,
) {
    fun asDomain(): Author {
        return Author(
            id = id,
            title = title,
            cover = cover,
            authorName = authorName,
            category = category,
            rating = rating
        )
    }
}