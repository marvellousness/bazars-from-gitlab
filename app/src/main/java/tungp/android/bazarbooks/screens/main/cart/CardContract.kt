package tungp.android.bazarbooks.screens.main.cart

import tungp.android.bazarbooks.domain.model.CartItem

data class CartState(
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalPrice: Double = 0.0
)

sealed class CartEvent {
    object LoadCart : CartEvent()
    data class UpdateQuantity(val cartItemId: String, val quantity: Int) : CartEvent()
    data class RemoveFromCart(val cartItemId: String) : CartEvent()
    object ClearCart : CartEvent()
}