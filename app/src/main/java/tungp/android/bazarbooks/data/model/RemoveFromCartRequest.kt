package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoveFromCartRequest(
    val cartId: String
)