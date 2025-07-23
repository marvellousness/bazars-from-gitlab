package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val repository: RemoteRepository
) : DataStateUseCase<String, Book>() {
    override suspend fun FlowCollector<BazaResult<Book>>.execute(params: String) {
        repository.getBookDetail(bookId = params).collect { result ->
            emit(result)
        }
    }
}