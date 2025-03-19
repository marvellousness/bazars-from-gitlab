package tungp.android.bazarbooks.screens.main.booksheet

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.usecase.AddToCartParams
import tungp.android.bazarbooks.domain.usecase.AddToCartUseCase
import tungp.android.bazarbooks.domain.usecase.GetBookDetailUseCase
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailBottomSheetViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : MviViewModel<BookDetailBottomSheetState, BookDetailBottomSheetEvent>() {

    private val _quantity = MutableStateFlow(1)
    val quantity: StateFlow<Int> = _quantity.asStateFlow()

    private val _addToCartResult = MutableStateFlow<CartItem?>(null)
    val addToCartResult: StateFlow<CartItem?> = _addToCartResult.asStateFlow()

    override fun onTriggerEvent(eventType: BookDetailBottomSheetEvent) {
        when (eventType) {
            is BookDetailBottomSheetEvent.LoadBookDetail -> onLoadBookDetail(eventType.bookId)
            is BookDetailBottomSheetEvent.UpdateQuantity -> onUpdateQuantity(eventType.quantity)
            is BookDetailBottomSheetEvent.AddToCart -> onAddToCart(
                eventType.bookId,
                eventType.quantity
            )

            is BookDetailBottomSheetEvent.ContinueShopping -> {} // Handled by composable
            is BookDetailBottomSheetEvent.Dismiss -> clearState()
        }
    }

    private fun onLoadBookDetail(bookId: String) = safeLaunch {
        // Reset the addToCartResult to avoid unwanted effects
        _addToCartResult.value = null

        setState(BaseViewState.Loading)
        execute(getBookDetailUseCase(params = bookId)) { bookDetail ->
            setData(
                BookDetailBottomSheetState(
                    book = bookDetail,
                    quantity = _quantity.value
                )
            )
        }
    }

    private fun onUpdateQuantity(quantity: Int) {
        if (quantity < 1 || quantity > 10) return // Validate quantity range

        _quantity.value = quantity
        (uiState.value as? BaseViewState.Data)?.let {
            val currentState = it.value
            setData(
                currentState.copy(
                    quantity = quantity
                )
            )
        }
    }

    private fun onAddToCart(bookId: String, quantity: Int) = safeLaunch {
        // Reset result first
        _addToCartResult.value = null

        // Validate quantity
        if (quantity <= 0) return@safeLaunch

        // Update state to show loading
        (uiState.value as? BaseViewState.Data)?.let {
            val currentState = it.value
            setData(
                currentState.copy(
                    isAddingToCart = true
                )
            )
        }

        execute(addToCartUseCase(params = AddToCartParams(bookId, quantity))) { cardItem ->
            // Update state with result
            (uiState.value as? BaseViewState.Data)?.let {
                val currentState = it.value
                setData(
                    currentState.copy(
                        isAddingToCart = false,
                        addToCartSuccess = cardItem
                    )
                )
            }

            _addToCartResult.value = cardItem
        }
    }

    private fun clearState() {
        _quantity.value = 1
        _addToCartResult.value = null
        setState(BaseViewState.Empty)
    }

    override fun onCleared() {
        super.onCleared()
        // Clear any resources if needed
        clearState()
    }
}