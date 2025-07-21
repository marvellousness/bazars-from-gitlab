package tungp.android.bazarbooks.screens.order.model

import java.math.BigDecimal

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Long = 0,
)