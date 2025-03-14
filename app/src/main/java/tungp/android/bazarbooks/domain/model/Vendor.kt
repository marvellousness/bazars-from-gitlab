package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Vendor(
    val id: String,
    val title: String,
    val cover: String,
    val authorName: String,
    val booksCount: Int,
    val rating: Int,
)