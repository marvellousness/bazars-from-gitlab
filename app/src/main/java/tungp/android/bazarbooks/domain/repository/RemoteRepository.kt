package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.model.HomeFeedsDomainModel
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.data.model.base.*

interface RemoteRepository {
    suspend fun getHomeFeeds(): Flow<BazaResult<HomeFeedsDomainModel>>
    suspend fun getCategories(): Flow<BazaResult<Categories>>
    suspend fun getVendors(): Flow<BazaResult<List<Vendor>>>
}