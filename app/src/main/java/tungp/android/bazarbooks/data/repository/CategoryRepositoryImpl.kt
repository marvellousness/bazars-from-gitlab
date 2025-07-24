package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.model.Category
import tungp.android.bazarbooks.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CategoryRepository {
    override fun getCategories(): Flow<BazaResult<Categories>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getCategories()
            when {
                response.statusCode == 200 && response.data != null -> {
                    // Convert API response to domain model
                    val domainModel = Categories(
                        categories = response.data.categories.map { category ->
                            Category(
                                id = category.id,
                                name = category.name,
                                description = category.description,
                                imageUrl = category.imageUrl,
                                bookCount = category.bookCount
                            )
                        }
                    )
                    emit(BazaResult.Success(domainModel))
                }

                else -> {
                    emit(BazaResult.Error(Exception(response.statusMessage)))
                }
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)
}
