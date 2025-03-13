package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.SerialName

data class HomeFeedsDomainModel(
    @SerialName("topOfWeek")
    val topOfWeek: List<Book> = emptyList(),

    @SerialName("bestVendors")
    val bestVendors: List<Vendor> = emptyList(),

    @SerialName("authors")
    val authors: List<Author> = emptyList(),
)