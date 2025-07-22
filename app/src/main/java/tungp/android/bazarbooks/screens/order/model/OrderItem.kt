package tungp.android.bazarbooks.screens.order.model

data class OrderItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Long = 0,
)