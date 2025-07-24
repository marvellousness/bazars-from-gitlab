package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tungp.android.bazarbooks.data.local.dao.AuthorDao
import tungp.android.bazarbooks.data.local.database.BookDatabase
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.repository.AuthorRepository
import javax.inject.Inject

class AuthorRepositoryImpl @Inject constructor(
    private val authorDao: AuthorDao,
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthorRepository {
    override fun getAuthors(): Flow<BazaResult<List<Author>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getAuthors()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.authors))
            } else {
                emit(
                    BazaResult.Error(
                        Exception(
                            response.statusMessage
                        )
                    )
                )
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)
}
