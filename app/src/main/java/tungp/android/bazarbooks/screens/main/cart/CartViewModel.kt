package tungp.android.bazarbooks.screens.main.cart

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.usecase.ClearCartUseCase
import tungp.android.bazarbooks.domain.usecase.GetCartUseCase
import tungp.android.bazarbooks.domain.usecase.NoParams
import tungp.android.bazarbooks.domain.usecase.RemoveCartItemUseCase
import tungp.android.bazarbooks.domain.usecase.RemoveItemFromCartParams
import tungp.android.bazarbooks.domain.usecase.UpdateCartItemParams
import tungp.android.bazarbooks.domain.usecase.UpdateCartItemUseCase
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val removeCartItemUseCase: RemoveCartItemUseCase,
    private val clearCartUseCase: ClearCartUseCase,
) : MviViewModel<CartState, CartEvent>() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    init {
        onTriggerEvent(CartEvent.LoadCart)
    }

    override fun onTriggerEvent(eventType: CartEvent) {
        when (eventType) {
            is CartEvent.LoadCart -> loadCart()
            is CartEvent.UpdateQuantity -> updateQuantity(eventType.cartItemId, eventType.quantity)
            is CartEvent.RemoveFromCart -> removeFromCart(eventType.cartItemId)
            is CartEvent.ClearCart -> clearCart()
        }
    }

    private fun loadCart() = safeLaunch {
        setState(BaseViewState.Loading)
        execute(getCartUseCase(NoParams)) { items ->
            setData(
                CartState(
                    cartItems = items,
                    totalPrice = calculateTotalPrice(items)
                )
            )
        }
    }

    private fun updateQuantity(cartItemId: String, quantity: Int) = safeLaunch {
        if (quantity < 1) return@safeLaunch

        execute(updateCartItemUseCase(UpdateCartItemParams(cartItemId, quantity))) { updatedItem ->
            val currentItems = (_cartItems.value).toMutableList()
            val index = currentItems.indexOfFirst { it.id == cartItemId }
            if (index != -1) {
                currentItems[index] = updatedItem
                setData(
                    CartState(
                        cartItems = currentItems,
                        totalPrice = calculateTotalPrice(currentItems)
                    )
                )
            }
        }
    }

    private fun removeFromCart(cartItemId: String) = safeLaunch {
        // Get the current cart items from the state
        val currentState = (uiState.value as? BaseViewState.Data)?.value
        val currentItems = currentState?.cartItems?.toMutableList() ?: return@safeLaunch

        // Remove the item locally
        val newItems = currentItems.filter { it.id != cartItemId }

        setData(
            CartState(
                cartItems = newItems,
                totalPrice = calculateTotalPrice(newItems)
            )
        )

        // Call backend to remove the item
        execute(removeCartItemUseCase(RemoveItemFromCartParams(cartItemId))) { remainItems ->
            setData(
                CartState(
                    cartItems = remainItems,
                    totalPrice = calculateTotalPrice(remainItems)
                )
            )
        }
    }

    private fun clearCart() = safeLaunch {
        setState(BaseViewState.Loading)
        execute(clearCartUseCase(NoParams)) { success ->
            if (success) {
                setData(CartState())
            }
        }
    }

    private fun calculateTotalPrice(items: List<CartItem>): Double {
        return items.sumOf { it.totalPrice }
    }
} 