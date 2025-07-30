package tungp.android.bazarbooks.data.remote.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    val id: String,
    val userId: String,
    val bookId: String,
    val quantity: Int,
    val status: String
)
