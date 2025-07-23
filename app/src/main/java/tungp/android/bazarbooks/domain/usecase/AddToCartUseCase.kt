package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

data class AddToCartParams(
    val bookId: String,
    val quantity: Int
)

class AddToCartUseCase @Inject constructor(
    private val repository: RemoteRepository
) : DataStateUseCase<AddToCartParams, CartItem>() {
    override suspend fun FlowCollector<BazaResult<CartItem>>.execute(params: AddToCartParams) {
        repository.addToCart(bookId = params.bookId, quantity = params.quantity).collect { result ->
            emit(result)
        }
    }
} 