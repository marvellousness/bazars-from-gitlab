package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.model.response.ConfirmOrderResponse
import tungp.android.bazarbooks.domain.repository.CartRepository
import javax.inject.Inject


data class ConfirmOrderParams(
    val bookId: String,
    val quantity: Int
)

class ConfirmOrderUseCase @Inject constructor(
    private val repository: CartRepository
) : DataStateUseCase<ConfirmOrderParams, ConfirmOrderResponse>() {
    override suspend fun FlowCollector<BazaResult<ConfirmOrderResponse>>.execute(params: ConfirmOrderParams) {
        repository.confirmOrder().collect { result ->
            emit(result)
        }
    }
} 