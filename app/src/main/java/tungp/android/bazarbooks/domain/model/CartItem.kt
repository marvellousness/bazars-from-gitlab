package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: String,
    val bookId: String,
    val title: String,
    val cover: String,
    val author: String,
    val quantity: Int,
    val price: Double,
    val totalPrice: Double = quantity * price
) 