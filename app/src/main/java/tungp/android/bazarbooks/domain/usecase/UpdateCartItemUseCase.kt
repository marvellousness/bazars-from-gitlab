package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

data class UpdateCartItemParams(
    val cartItemId: String,
    val quantity: Int
)

class UpdateCartItemUseCase @Inject constructor(
    private val repository: RemoteRepository
) : DataStateUseCase<UpdateCartItemParams, CartItem>() {
    override suspend fun FlowCollector<BazaResult<CartItem>>.execute(params: UpdateCartItemParams) {
        repository.updateCartItem(
            cartItemId = params.cartItemId,
            quantity = params.quantity
        ).collect { result ->
            emit(result)
        }
    }
} 