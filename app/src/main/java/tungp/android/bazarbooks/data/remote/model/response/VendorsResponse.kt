package tungp.android.bazarbooks.data.remote.model.response

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Vendor

@Serializable
data class VendorsResponse(
    val vendors: List<Vendor> = emptyList()
)