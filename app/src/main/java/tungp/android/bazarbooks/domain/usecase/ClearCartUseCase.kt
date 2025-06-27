package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val repository: RemoteRepository
) : DataStateUseCase<NoParams, Boolean>() {
    override suspend fun FlowCollector<BazaResult<Boolean>>.execute(params: NoParams) {
        repository.clearCart().collect { result ->
            emit(result)
        }
    }
} 