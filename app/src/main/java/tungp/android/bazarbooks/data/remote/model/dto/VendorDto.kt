package tungp.android.bazarbooks.data.remote.model.dto

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Vendor

@Serializable
data class VendorDto(
    val id: String,
    val title: String,
    val cover: String,
    val authorName: String,
    val booksCount: Int,
    val rating: Int,
    val category: String = "Books" // Default category
) {
    fun asDomain(): Vendor {
        return Vendor(
            id = id,
            title = title,
            cover = cover,
            authorName = authorName,
            booksCount = booksCount,
            rating = rating,
            category = category
        )
    }
}