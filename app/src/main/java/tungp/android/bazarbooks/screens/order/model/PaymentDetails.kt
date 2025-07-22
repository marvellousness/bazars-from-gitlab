package tungp.android.bazarbooks.screens.order.model

data class PaymentDetails(
    val price: Long = 0,
    val shipping: Long = 0,
    val total: Long = 0,
    val currency: String,
    val orders: List<OrderItem>,
)