package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.HomeFeedsDomainModel
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetHomeFeedsUseCase
@Inject constructor(
   private val repository: RemoteRepository,
) : DataStateUseCase<NoParams, HomeFeedsDomainModel>() {
   override suspend fun FlowCollector<BazaResult<HomeFeedsDomainModel>>.execute(params: NoParams) {
        repository.getHomeFeeds().collect { result ->
            emit(result)
        }
   }
}