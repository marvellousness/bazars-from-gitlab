package tungp.android.bazarbooks.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.Vendor

@Serializable
data class HomeFeedsResponse(
    @SerialName("specialOffers")
    val specialOffers: List<Book> = emptyList(),

    @SerialName("topOfWeek")
    val topOfWeek: List<Book> = emptyList(),

    @SerialName("bestVendors")
    val bestVendors: List<Vendor> = emptyList(),

    @SerialName("authors")
    val authors: List<Author> = emptyList(),
)