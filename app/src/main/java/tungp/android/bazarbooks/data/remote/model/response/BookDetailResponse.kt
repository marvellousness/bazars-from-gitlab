package tungp.android.bazarbooks.data.remote.model.response

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Book

@Serializable
data class BookDetailResponse(
    val book: Book
)