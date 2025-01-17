package tungp.android.bazarbooks.data.repository

import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.data.remote.network.model.response.BookResponse
import tungp.android.bazarbooks.data.remote.network.model.response.HomeFeedsResponse
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteRepository {

    override suspend fun getHomeFeeds(): Result<HomeFeedsResponse> {
        return apiService.getHomeFeeds()
    }

    override suspend fun getPopularBooks(): Result<List<Book>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecentReads(): Result<List<Book>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteByUserId(userId: String): Result<List<Book>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetailsById(bookId: String): Result<BookResponse> {
        TODO("Not yet implemented")
    }
}