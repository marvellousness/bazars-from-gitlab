package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tungp.android.bazarbooks.data.model.AddToCartRequest
import tungp.android.bazarbooks.data.model.ConfirmOrderResponse
import tungp.android.bazarbooks.data.model.RemoveFromCartRequest
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.model.Category
import tungp.android.bazarbooks.domain.model.HomeFeedsDomainModel
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteRepository {

    override fun getHomeFeeds(): Flow<BazaResult<HomeFeedsDomainModel>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getHomeFeeds()
            if (response.statusCode == 200 && response.data != null) {
                // Convert API response to domain model
                val domainModel = HomeFeedsDomainModel(
                    specialOffers = response.data.specialOffers,
                    topOfWeek = response.data.topOfWeek,
                    bestVendors = response.data.bestVendors,
                    authors = response.data.authors
                )
                emit(BazaResult.Success(domainModel))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Unknown error occurred")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun getCategories(): Flow<BazaResult<Categories>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getCategories()
            if (response.statusCode == 200 && response.data != null) {
                // Convert API response to domain model
                val domainModel = Categories(
                    categories = response.data.categories.map { categoryResponse ->
                        Category(
                            id = categoryResponse.id,
                            name = categoryResponse.name,
                            description = categoryResponse.description,
                            imageUrl = categoryResponse.imageUrl,
                            bookCount = categoryResponse.bookCount
                        )
                    }
                )
                emit(BazaResult.Success(domainModel))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Unknown error occurred")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun getVendors(): Flow<BazaResult<List<Vendor>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getVendors()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.vendors))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Unknown error occurred")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun getAuthors(): Flow<BazaResult<List<Author>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getAuthors()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.authors))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Unknown error occurred")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun getBookDetail(bookId: String): Flow<BazaResult<Book>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getBookDetails(bookId)
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.book))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun addToCart(bookId: String, quantity: Int): Flow<BazaResult<CartItem>> = flow {
        emit(BazaResult.Loading)
        try {
            val request = AddToCartRequest(bookId = bookId, quantity = quantity)
            val response = apiService.addToCart(request)

            if (response.statusCode == 200 && response.data != null) {
                // Return success flag from response
                emit(BazaResult.Success(response.data.cartItem))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Failed to add to cart")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun getCart(): Flow<BazaResult<List<CartItem>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getCart()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.cartItems))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Failed to get cart")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun updateCartItem(cartItemId: String, quantity: Int): Flow<BazaResult<CartItem>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.updateCartItem(cartItemId, quantity)
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.cartItem))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Failed to update cart item")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun removeFromCart(cartItemId: String): Flow<BazaResult<List<CartItem>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.removeFromCart(RemoveFromCartRequest(cartItemId))
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.cartItems))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Failed to remove cart item")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun clearCart(): Flow<BazaResult<Boolean>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.clearCart()
            if (response.statusCode == 200) {
                emit(BazaResult.Success(true))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Failed to clear cart")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override fun confirmOrder(): Flow<BazaResult<ConfirmOrderResponse>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.confirmOrder()
            emit(BazaResult.Success(response.data ?: throw Exception("Failed to confirm order")))
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }
}