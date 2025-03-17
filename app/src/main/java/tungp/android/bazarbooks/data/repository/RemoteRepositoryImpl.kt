package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.model.Category
import tungp.android.bazarbooks.domain.model.HomeFeedsDomainModel
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteRepository {

    override suspend fun getHomeFeeds(): Flow<BazaResult<HomeFeedsDomainModel>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getHomeFeeds()
            if (response.statusCode == 200 && response.data != null) {
                // Convert API response to domain model
                val domainModel = HomeFeedsDomainModel(
                    specialOffers = response.data.specialOffers,
                    topOfWeek = response.data.topOfWeek,
                    bestVendors = response.data.bestVendors,
                    authors = response.data.authors
                )
                emit(BazaResult.Success(domainModel))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Unknown error occurred")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }
    
    override suspend fun getCategories(): Flow<BazaResult<Categories>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getCategories()
            if (response.statusCode == 200 && response.data != null) {
                // Convert API response to domain model
                val domainModel = Categories(
                    categories = response.data.categories.map { categoryResponse ->
                        Category(
                            id = categoryResponse.id,
                            name = categoryResponse.name,
                            description = categoryResponse.description,
                            imageUrl = categoryResponse.imageUrl,
                            bookCount = categoryResponse.bookCount
                        )
                    }
                )
                emit(BazaResult.Success(domainModel))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Unknown error occurred")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }
    
    override suspend fun getVendors(): Flow<BazaResult<List<Vendor>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getVendors()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.vendors))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage ?: "Unknown error occurred")))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }

    override suspend fun getBookDetail(bookId: String): Flow<BazaResult<Book>> = flow {
        emit(BazaResult.Loading)
        try {
            //val response = apiService.getBookDetails(bookId)
            val response = apiService.getBookDetails()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.book))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }
}