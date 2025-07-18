package tungp.android.bazarbooks.screens.order

import java.math.BigDecimal

data class PaymentDetails(
    val price: BigDecimal,
    val shipping: BigDecimal,
    val total: BigDecimal,
    val currency: String,
    val orders: List<OrderItem>,
)

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: BigDecimal,
)

data class AddressDetails(
    val title: String,
    val subTitle: String,
)