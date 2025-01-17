package tungp.android.bazarbooks

import tungp.android.bazarbooks.domain.model.Book

data class MainViewState(
    val topOfWeek: List<Book> = emptyList(),
)

sealed class MainViewEvent {
    object LoadHomeFeeds : MainViewEvent()
}

