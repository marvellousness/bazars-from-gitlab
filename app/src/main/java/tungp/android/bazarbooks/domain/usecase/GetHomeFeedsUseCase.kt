package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.network.model.response.HomeFeedsResponse
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetHomeFeedsUseCase
@Inject constructor(
    private val repository: RemoteRepository,
) : DataStateUseCase<NoParams, HomeFeedsResponse>() {
    override suspend fun FlowCollector<Result<HomeFeedsResponse>>.execute(params: NoParams) {
        emit(repository.getHomeFeeds())
    }
}