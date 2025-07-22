package tungp.android.bazarbooks.screens.order.success

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.components.Divider
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.screens.order.OrderSampleData
import tungp.android.bazarbooks.screens.order.model.OrderItem
import tungp.android.bazarbooks.screens.order.model.PaymentDetails
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale200
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.Primary50
import tungp.android.bazarbooks.ui.theme.Primary500
import tungp.android.bazarbooks.ui.theme.paddingDefault


@Composable
fun OrderSuccessScreen(
    navController: NavController,
) {
    val paymentDetails = OrderSampleData.paymentDetails

    BazarSurface(backgroundColor = BazarTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paddingDefault()
                .verticalScroll(rememberScrollState()),
        ) {
            HeaderSection()
            OrderDetailSection(paymentDetails)
            Spacer(modifier = Modifier.weight(1f))
            OrderActionSection(onClick = {})
        }
    }
}

@Composable
private fun OrderActionSection(onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        PrimaryButton(
            text = "Order Status",
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick
        )
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.requiredHeight(32.dp))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Primary50)
            .fillMaxSize()
            .padding(vertical = 24.dp)
    ) {
        Text(
            text = "Thankyou 👋",
            color = GrayScale900,
            style = BazarTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.requiredHeight(8.dp))
        Text(
            text = "Lorem ipsum dolor sit",
            color = Primary500,
            style = BazarTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.requiredHeight(16.dp))
        Text(
            text = "Order #2930541",
            color = GrayScale900,
            style = BazarTheme.typography.bodyMedium
        )
    }
}

@Composable
fun OrderDetailSection(
    paymentDetails: PaymentDetails,
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Order Details",
        color = GrayScale900,
        style = BazarTheme.typography.titleMedium,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .border(
                width = 1.dp, color = GrayScale200, shape = RoundedCornerShape(8.dp)
            )
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OrderList(paymentDetails.orders)
        Divider(modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Subtotal",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                "$100",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        Divider(modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Shipping",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                "$100",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        Divider(modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(
                "Total Payment",
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = GrayScale900,
            )
            Text(
                "${paymentDetails.currency} ${paymentDetails.total}",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(
                "Delivery in",
                style = BazarTheme.typography.bodyMedium,
                color = GrayScale900,
            )
            Text(
                "10 - 15 minus",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(
                "Time",
                style = BazarTheme.typography.bodyMedium,
                color = GrayScale900,
            )
            Text(
                "15.24 - 15.39",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun OrderItemView(
    item: OrderItem,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = item.quantity.toString() + "x",
            color = GrayScale900,
            style = BazarTheme.typography.bodyMedium,
        )
        Text(
            text = item.name,
            color = GrayScale900,
            style = BazarTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = "$ ${item.price}",
            color = GrayScale900,
            textAlign = TextAlign.End,
            style = BazarTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun OrderList(orders: List<OrderItem>, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = modifier
    ) {
        orders.forEachIndexed { index, item ->
            OrderItemView(item = item, modifier = modifier)
        }
    }
}


@Preview
@Composable
private fun OrderSuccessScreenPreview() {
    OrderSuccessScreen(rememberNavController())
}