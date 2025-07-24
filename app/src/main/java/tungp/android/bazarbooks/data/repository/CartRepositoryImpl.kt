package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.model.request.AddToCartRequest
import tungp.android.bazarbooks.data.remote.model.request.RemoveFromCartRequest
import tungp.android.bazarbooks.data.remote.model.response.ConfirmOrderResponse
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CartRepository {

    override fun addToCart(bookId: String, quantity: Int): Flow<BazaResult<CartItem>> = flow {
        emit(BazaResult.Loading)
        try {
            val request = AddToCartRequest(bookId = bookId, quantity = quantity)
            val response = apiService.addToCart(request)

            if (response.statusCode == 200 && response.data != null) {
                // Return success flag from response
                emit(BazaResult.Success(response.data.cartItem))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)

    override fun getCart(): Flow<BazaResult<List<CartItem>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getCart()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.cartItems))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)

    override fun updateCartItem(cartItemId: String, quantity: Int): Flow<BazaResult<CartItem>> =
        flow {
            emit(BazaResult.Loading)
            try {
                val response = apiService.updateCartItem(cartItemId, quantity)
                if (response.statusCode == 200 && response.data != null) {
                    emit(BazaResult.Success(response.data.cartItem))
                } else {
                    emit(
                        BazaResult.Error(
                            Exception(
                                response.statusMessage
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                emit(BazaResult.Error(e))
            }
        }.flowOn(ioDispatcher)

    override fun removeFromCart(cartItemId: String): Flow<BazaResult<List<CartItem>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.removeFromCart(RemoveFromCartRequest(cartItemId))
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.cartItems))
            } else {
                emit(
                    BazaResult.Error(
                        Exception(
                            response.statusMessage
                        )
                    )
                )
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)

    override fun clearCart(): Flow<BazaResult<Boolean>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.clearCart()
            if (response.statusCode == 200) {
                emit(BazaResult.Success(true))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)

    override fun confirmOrder(): Flow<BazaResult<ConfirmOrderResponse>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.confirmOrder()
            emit(BazaResult.Success(response.data ?: throw Exception("Failed to confirm order")))
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)
}