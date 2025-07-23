package tungp.android.bazarbooks.data.remote.model.dto

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Book

@Serializable
data class BookDto(
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
) {
    fun asDomain(): Book {
        return Book(
            bookId = bookId ?: "",
            isbn = isbn,
            title = title,
            cover = cover,
            author = author,
            price = price ?: 0.0,
            genre = genre,
            publishedYear = publishedYear ?: 0,
            description = description ?: "",
            publisher = publisher ?: "",
            numberOfPages = numberOfPages ?: 0,
            quantityInStock = quantityInStock,
            rating = rating
        )
    }
}