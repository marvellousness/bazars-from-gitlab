package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetCategoriesUseCase
@Inject constructor(
   private val repository: RemoteRepository,
) : DataStateUseCase<NoParams, Categories>() {
   override suspend fun FlowCollector<BazaResult<Categories>>.execute(params: NoParams) {
        repository.getCategories().collect { result ->
            emit(result)
        }
   }
} 