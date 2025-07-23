package tungp.android.bazarbooks.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.CartItem

@Serializable
data class CartResponse(
    @SerialName("items")
    val cartItems: List<CartItem>
)

@Serializable
data class UpdateCartResponse(
    val cartItem: CartItem
)

@Serializable
data class RemoveCartResponse(
    @SerialName("items")
    val cartItems: List<CartItem>
)

@Serializable
data class ClearCartResponse(
    val success: Boolean
)