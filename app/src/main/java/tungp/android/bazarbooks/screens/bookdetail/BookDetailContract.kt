package tungp.android.bazarbooks.screens.bookdetail

import tungp.android.bazarbooks.domain.model.Book

// State class for the book detail screen
data class BookDetailState(
    val book: Book = Book()
)

// Event sealed class for the book detail screen
sealed class BookDetailEvent {
    data class LoadBookDetail(val bookId: String) : BookDetailEvent()
}