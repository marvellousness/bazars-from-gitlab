package tungp.android.bazarbooks.domain.repository

import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.data.remote.network.model.response.BookResponse
import tungp.android.bazarbooks.data.remote.network.model.response.HomeFeedsResponse

interface RemoteRepository {
    suspend fun getHomeFeeds(): Result<HomeFeedsResponse>
    suspend fun getPopularBooks(): Result<List<Book>>
    suspend fun getRecentReads(): Result<List<Book>>
    suspend fun getFavoriteByUserId(userId: String): Result<List<Book>>
    suspend fun getBookDetailsById(bookId: String): Result<BookResponse>
}