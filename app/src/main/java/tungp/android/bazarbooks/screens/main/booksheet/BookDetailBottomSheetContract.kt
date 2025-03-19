package tungp.android.bazarbooks.screens.main.booksheet

import tungp.android.bazarbooks.domain.model.Book


data class BookDetailBottomSheetState(
    val book: Book? = null,
    val quantity: Int = 1,
    val isAddingToCart: Boolean = false,
    val addToCartSuccess: Boolean? = null
)

sealed class BookDetailBottomSheetEvent {
    data class LoadBookDetail(val bookId: String) : BookDetailBottomSheetEvent()
    data class UpdateQuantity(val quantity: Int) : BookDetailBottomSheetEvent()
    data class AddToCart(val bookId: String, val quantity: Int) : BookDetailBottomSheetEvent()
    object ContinueShopping : BookDetailBottomSheetEvent()
    object Dismiss : BookDetailBottomSheetEvent()
}