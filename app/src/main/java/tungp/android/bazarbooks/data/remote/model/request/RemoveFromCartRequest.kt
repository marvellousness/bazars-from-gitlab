package tungp.android.bazarbooks.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RemoveFromCartRequest(
    val cartId: String
)