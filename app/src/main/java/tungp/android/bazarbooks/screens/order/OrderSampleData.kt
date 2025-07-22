package tungp.android.bazarbooks.screens.order

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Payment
import androidx.compose.ui.graphics.Color
import tungp.android.bazarbooks.screens.order.model.AddressDetails
import tungp.android.bazarbooks.screens.order.model.OrderItem
import tungp.android.bazarbooks.screens.order.model.PaymentDetails

object OrderSampleData {
    val ordersList: List<OrderItem> = listOf(
        OrderItem(
            id = 1,
            name = "Carrie Fisher",
            price = 1999,
            quantity = 1
        ),
        OrderItem(
            id = 2,
            name = "The Da Vinci Code",
            price = 3999,
            quantity = 1
        ),
        OrderItem(
            id = 3,
            name = "Squid Sweet and Sour Salad",
            quantity = 2,
            price = 400
        ),
    )

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
        price = 100,
        shipping = 100,
        total = 200,
        currency = "$",
        orders = ordersList
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

    val addressDetails = AddressDetails(
        title = "Utama Street No.20",
        subTitle = "Dumbo Street No.20, Dumbo, New York 10001, United States of America"
    )
    val officeAddresses = listOf("Home", "Office")

}