package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val id: String,
    val title: String,
    val cover: String,
    val authorName: String,
)