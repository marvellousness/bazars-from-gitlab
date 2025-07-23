package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.HomeFeeds
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetHomeFeedsUseCase
@Inject constructor(
    private val repository: RemoteRepository,
) : DataStateUseCase<NoParams, HomeFeeds>() {
    override suspend fun FlowCollector<BazaResult<HomeFeeds>>.execute(params: NoParams) {
        repository.getHomeFeeds().collect { result ->
            emit(result)
        }
    }
}