package tungp.android.bazarbooks.data.remote.network.model.response

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Book

@Serializable
class BookResponse(val book: Book) : BaseResponse()
