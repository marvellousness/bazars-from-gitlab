package tungp.android.bazarbooks.screens.order

import tungp.android.bazarbooks.domain.model.CartItem

data class OrderState(
    val cartItem: CartItem,
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalPrice: Double = 0.0
)

sealed class OrderEvent {
    object LoadCartDetail : OrderEvent()
}