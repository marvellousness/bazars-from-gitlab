package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repository: RemoteRepository
) : DataStateUseCase<NoParams, List<CartItem>>() {
    override suspend fun FlowCollector<BazaResult<List<CartItem>>>.execute(params: NoParams) {
        repository.getCart().collect { result ->
            emit(result)
        }
    }
} 