package tungp.android.bazarbooks.screens.main

import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.Vendor

data class HomeState(
    val topOfWeeks: List<Book> = emptyList(),
    val bestVendors: List<Vendor> = emptyList(),
    val authors: List<Author> = emptyList(),
)


sealed class HomeEvent {
    object LoadHomeFeeds : HomeEvent()
}