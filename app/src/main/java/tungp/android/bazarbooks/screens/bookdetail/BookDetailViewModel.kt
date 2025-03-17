package tungp.android.bazarbooks.screens.bookdetail

import tungp.android.bazarbooks.mvi.BaseViewState

import dagger.hilt.android.lifecycle.HiltViewModel
import tungp.android.bazarbooks.domain.usecase.GetBookDetailUseCase
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase
) : MviViewModel<BookDetailState, BookDetailEvent>() {

    override fun onTriggerEvent(eventType: BookDetailEvent) {
        when (eventType) {
            is BookDetailEvent.LoadBookDetail -> onLoadBookDetail(eventType.bookId)
        }
    }

    private fun onLoadBookDetail(bookId: String) = safeLaunch {
        setState(BaseViewState.Loading)
        execute(getBookDetailUseCase(params = bookId)) { bookDetail ->
            setData(
                BookDetailState(
                    book = bookDetail
                )
            )
        }
    }
}