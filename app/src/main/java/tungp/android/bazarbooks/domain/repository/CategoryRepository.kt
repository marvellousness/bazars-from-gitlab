package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Categories

interface CategoryRepository {
    fun getCategories(): Flow<BazaResult<Categories>>
}
