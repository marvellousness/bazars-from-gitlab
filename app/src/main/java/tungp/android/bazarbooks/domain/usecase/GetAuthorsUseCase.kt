package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetAuthorsUseCase
@Inject constructor(
    private val repository: RemoteRepository,
) : DataStateUseCase<NoParams, List<Author>>() {
    override suspend fun FlowCollector<BazaResult<List<Author>>>.execute(params: NoParams) {
        repository.getAuthors().collect { result ->
            emit(result)
        }
    }
} 