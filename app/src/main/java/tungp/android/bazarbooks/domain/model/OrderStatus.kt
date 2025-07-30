package tungp.android.bazarbooks.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class OrderStatus {
    PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
}