package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Book

import tungp.android.bazarbooks.domain.model.HomeFeeds

interface BookRepository {
    fun getHomeFeeds(): Flow<BazaResult<HomeFeeds>>
    fun getBookDetail(bookId: String): Flow<BazaResult<Book>>
}
