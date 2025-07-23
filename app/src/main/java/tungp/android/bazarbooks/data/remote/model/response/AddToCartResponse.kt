package tungp.android.bazarbooks.data.remote.model.response

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.CartItem

@Serializable
data class AddToCartResponse(
    val cartItem: CartItem
)