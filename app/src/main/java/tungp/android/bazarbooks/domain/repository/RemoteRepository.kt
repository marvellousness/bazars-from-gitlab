package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.model.HomeFeedsDomainModel
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.data.model.base.*
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.model.Author

interface RemoteRepository {
    fun getHomeFeeds(): Flow<BazaResult<HomeFeedsDomainModel>>
    fun getCategories(): Flow<BazaResult<Categories>>
    fun getVendors(): Flow<BazaResult<List<Vendor>>>
    fun getAuthors(): Flow<BazaResult<List<Author>>>
    fun getBookDetail(bookId: String): Flow<BazaResult<Book>>
    fun addToCart(bookId: String, quantity: Int): Flow<BazaResult<CartItem>>
    fun getCart(): Flow<BazaResult<List<CartItem>>>
    fun updateCartItem(cartItemId: String, quantity: Int): Flow<BazaResult<CartItem>>
    fun removeFromCart(cartItemId: String): Flow<BazaResult<Boolean>>
    fun clearCart(): Flow<BazaResult<Boolean>>
}