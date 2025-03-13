package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.domain.model.HomeFeedsDomainModel
import tungp.android.bazarbooks.data.model.base.*

interface RemoteRepository {
    suspend fun getHomeFeeds(): Flow<BazaResult<HomeFeedsDomainModel>>
}