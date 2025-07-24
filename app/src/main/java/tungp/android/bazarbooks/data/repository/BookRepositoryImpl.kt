package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tungp.android.bazarbooks.data.local.dao.BookDao
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.repository.BookRepository
import javax.inject.Inject

import tungp.android.bazarbooks.domain.model.HomeFeeds

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BookRepository {
    override fun getBookDetail(bookId: String): Flow<BazaResult<Book>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getBookDetails(bookId)
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.book))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)

    override fun getHomeFeeds(): Flow<BazaResult<HomeFeeds>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getHomeFeeds()
            when {
                response.statusCode == 200 && response.data != null -> {
                    // Convert API response to domain model
                    val domainModel = HomeFeeds(
                        specialOffers = response.data.specialOffers.map { bookDto -> bookDto.asDomainModel() },
                        topOfWeek = response.data.topOfWeek.map { bookDto -> bookDto.asDomainModel() },
                        bestVendors = response.data.bestVendors.map { vendorDto -> vendorDto.asDomainModel() },
                        authors = response.data.authors.map { authorDto -> authorDto.asDomainModel() }
                    )
                    emit(BazaResult.Success(domainModel))
                }

                else -> {
                    val errorMessage = response.statusMessage
                    emit(BazaResult.Error(Exception(errorMessage)))
                }
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)
}
