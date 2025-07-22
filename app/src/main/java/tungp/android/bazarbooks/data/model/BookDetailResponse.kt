package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Book

@Serializable
data class BookDetailResponse(
    val book: Book
) 