package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.CartItem

@Serializable
data class CartResponse(
    val cartItems: List<CartItem>
)

@Serializable
data class UpdateCartResponse(
    val cartItem: CartItem
)

@Serializable
data class RemoveCartResponse(
    val success: Boolean
)

@Serializable
data class ClearCartResponse(
    val success: Boolean
) 