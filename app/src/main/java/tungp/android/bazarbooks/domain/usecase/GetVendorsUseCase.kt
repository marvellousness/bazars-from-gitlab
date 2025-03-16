package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetVendorsUseCase
@Inject constructor(
   private val repository: RemoteRepository,
) : DataStateUseCase<NoParams, List<Vendor>>() {
   override suspend fun FlowCollector<BazaResult<List<Vendor>>>.execute(params: NoParams) {
        repository.getVendors().collect { result ->
            emit(result)
        }
   }
} 