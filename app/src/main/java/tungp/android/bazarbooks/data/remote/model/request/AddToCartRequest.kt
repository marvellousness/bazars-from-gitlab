package tungp.android.bazarbooks.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    val bookId: String,
    val quantity: Int
)