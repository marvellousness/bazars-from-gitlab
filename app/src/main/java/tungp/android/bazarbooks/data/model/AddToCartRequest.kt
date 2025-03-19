package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    val bookId: String,
    val quantity: Int
) 