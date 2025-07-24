package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.repository.CartRepository
import javax.inject.Inject

data class RemoveItemFromCartParams(
    val cartItemId: String,
)

class RemoveCartItemUseCase @Inject constructor(
    private val repository: CartRepository,
) : DataStateUseCase<RemoveItemFromCartParams, List<CartItem>>() {
    override suspend fun FlowCollector<BazaResult<List<CartItem>>>.execute(params: RemoveItemFromCartParams) {
        repository.removeFromCart(cartItemId = params.cartItemId).collect { result ->
            emit(result)
        }
    }
} 