package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoveCartItemUseCase @Inject constructor(
    private val repository: RemoteRepository
) : DataStateUseCase<String, Boolean>() {
    override suspend fun FlowCollector<BazaResult<Boolean>>.execute(params: String) {
        repository.removeFromCart(cartItemId = params).collect { result ->
            emit(result)
        }
    }
} 