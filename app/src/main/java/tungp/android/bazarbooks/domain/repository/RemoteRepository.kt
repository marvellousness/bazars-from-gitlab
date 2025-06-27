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
    suspend fun getHomeFeeds(): Flow<BazaResult<HomeFeedsDomainModel>>
    suspend fun getCategories(): Flow<BazaResult<Categories>>
    suspend fun getVendors(): Flow<BazaResult<List<Vendor>>>
    suspend fun getAuthors(): Flow<BazaResult<List<Author>>>
    suspend fun getBookDetail(bookId: String): Flow<BazaResult<Book>>
    suspend fun addToCart(bookId: String, quantity: Int): Flow<BazaResult<CartItem>>
    suspend fun getCart(): Flow<BazaResult<List<CartItem>>>
    suspend fun updateCartItem(cartItemId: String, quantity: Int): Flow<BazaResult<CartItem>>
    suspend fun removeFromCart(cartItemId: String): Flow<BazaResult<Boolean>>
    suspend fun clearCart(): Flow<BazaResult<Boolean>>
}