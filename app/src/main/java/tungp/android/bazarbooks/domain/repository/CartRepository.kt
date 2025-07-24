package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.data.remote.model.response.ConfirmOrderResponse

interface CartRepository {
    fun addToCart(bookId: String, quantity: Int): Flow<BazaResult<CartItem>>
    fun getCart(): Flow<BazaResult<List<CartItem>>>
    fun updateCartItem(cartItemId: String, quantity: Int): Flow<BazaResult<CartItem>>
    fun removeFromCart(cartItemId: String): Flow<BazaResult<List<CartItem>>>
    fun clearCart(): Flow<BazaResult<Boolean>>
    fun confirmOrder(): Flow<BazaResult<ConfirmOrderResponse>>
}