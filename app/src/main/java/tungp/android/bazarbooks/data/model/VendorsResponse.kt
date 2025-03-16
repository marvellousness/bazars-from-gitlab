package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Vendor

@Serializable
data class VendorsResponse(
    val vendors: List<Vendor> = emptyList()
) 