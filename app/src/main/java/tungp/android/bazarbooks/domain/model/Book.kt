package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val bookId: String? = null,
    val isbn: String = "",
    val title: String = "",
    val cover: String = "",
    val author: String = "",
    val price: Double? = null,
    val genre: List<String> = listOf(),
    val publishedYear: Int? = null,
    val description: String? = null,
    val publisher: String? = null,
    val numberOfPages: Int? = null,
    val quantityInStock: Int = 0,
    val rating: Int = 0
)