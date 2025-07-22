package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.model.ConfirmOrderResponse
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject


data class ConfirmOrderParams(
    val bookId: String,
    val quantity: Int
)

class ConfirmOrderUseCase @Inject constructor(
    private val repository: RemoteRepository
) : DataStateUseCase<ConfirmOrderParams, ConfirmOrderResponse>() {
    override suspend fun FlowCollector<BazaResult<ConfirmOrderResponse>>.execute(params: ConfirmOrderParams) {
        repository.confirmOrder().collect { result ->
            emit(result)
        }
    }
} 