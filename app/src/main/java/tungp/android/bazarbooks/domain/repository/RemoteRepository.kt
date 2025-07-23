package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.data.remote.model.response.ConfirmOrderResponse
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.model.HomeFeeds
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.model.Author

interface RemoteRepository {
    fun getHomeFeeds(): Flow<BazaResult<HomeFeeds>>
    fun getCategories(): Flow<BazaResult<Categories>>
    fun getVendors(): Flow<BazaResult<List<Vendor>>>
    fun getAuthors(): Flow<BazaResult<List<Author>>>
    fun getBookDetail(bookId: String): Flow<BazaResult<Book>>
    fun addToCart(bookId: String, quantity: Int): Flow<BazaResult<CartItem>>
    fun getCart(): Flow<BazaResult<List<CartItem>>>
    fun updateCartItem(cartItemId: String, quantity: Int): Flow<BazaResult<CartItem>>
    fun removeFromCart(cartItemId: String): Flow<BazaResult<List<CartItem>>>
    fun clearCart(): Flow<BazaResult<Boolean>>
    fun confirmOrder(): Flow<BazaResult<ConfirmOrderResponse>>
}