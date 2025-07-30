package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(
    val id: String,
    val orderId: String,
    val bookId: String,
    val quantity: Int,
    val price: Double,
    val bookTitle: String
)
