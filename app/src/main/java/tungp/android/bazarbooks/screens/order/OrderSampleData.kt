package tungp.android.bazarbooks.screens.order

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Payment
import androidx.compose.ui.graphics.Color
import java.math.BigDecimal

object OrderSampleData {
    val paymentMethodItems = listOf(
        SelectionItem(
            id = "knet",
            title = "KNET",
            icon = Icons.Default.Payment,
            iconColor = Color(0xFF2196F3)
        ),
        SelectionItem(
            id = "credit_card",
            title = "Credit Card",
            icon = Icons.Default.CreditCard,
            iconColor = Color(0xFFFF9800)
        )
    )

    val paymentDetails = PaymentDetails(
        price = BigDecimal(100.00),
        shipping = BigDecimal(100),
        total = BigDecimal(200),
        currency = "$",
        orders = listOf(
            OrderItem(
                name = "Squid Sweet and Sour Salad",
                quantity = 2,
                price = BigDecimal(40.00)
            ),
            OrderItem(
                name = "Japan Hainanese Sashimi",
                quantity = 1,
                price = BigDecimal(20.00)
            ),
            OrderItem(
                name = "Black Pepper Beef Lumpia",
                quantity = 1,
                price = BigDecimal(20.00)
            )
        )
    )

    val dateItems = listOf(
        DeliveryDateItem(
            id = "today",
            title = "Today",
            subTitle = "12 Jan"
        ),
        DeliveryDateItem(
            id = "tomorrow",
            title = "Tomorrow",
            subTitle = "13 Jan"
        )
    )
    val timeItems = listOf(
        DeliveryDateItem(
            id = "first",
            title = "Between",
            subTitle = "10PM : 11PM"
        ),
        DeliveryDateItem(
            id = "second",
            title = "Between",
            subTitle = "10PM : 11PM"
        )
    )

}