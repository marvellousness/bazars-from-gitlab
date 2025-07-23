package tungp.android.bazarbooks.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.data.remote.model.dto.AuthorDto
import tungp.android.bazarbooks.data.remote.model.dto.BookDto
import tungp.android.bazarbooks.data.remote.model.dto.VendorDto

@Serializable
data class HomeFeedsResponse(
    @SerialName("specialOffers")
    val specialOffers: List<BookDto> = emptyList(),

    @SerialName("topOfWeek")
    val topOfWeek: List<BookDto> = emptyList(),

    @SerialName("bestVendors")
    val bestVendors: List<VendorDto> = emptyList(),

    @SerialName("authors")
    val authors: List<AuthorDto> = emptyList(),
)