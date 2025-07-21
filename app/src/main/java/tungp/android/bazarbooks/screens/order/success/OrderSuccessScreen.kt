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
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tungp.android.bazarbooks.components.Divider
import tungp.android.bazarbooks.screens.order.OrderSampleData.paymentDetails
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale200
import tungp.android.bazarbooks.ui.theme.GrayScale500
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.Primary50
import tungp.android.bazarbooks.ui.theme.Primary500


@Composable
fun OrderSuccessScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(color = BazarTheme.colors.background)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.requiredHeight(24.dp))
        HeaderSection()
        CancelLink()
        OrderDetails()
    }
}

@Composable
fun CancelLink() {
    Text(
        lineHeight = 10.sp, text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = GrayScale500, fontSize = 14.sp
                )
            ) { append("Do you want to cancel your order? ") }
            withStyle(
                style = SpanStyle(
                    color = Primary500, fontSize = 14.sp
                )
            ) { append("Cancel") }
        })
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.requiredHeight(24.dp))
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Primary50)
            .fillMaxSize()
            .padding(
                start = 32.dp, end = 32.dp, top = 24.dp, bottom = 16.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Thankyou 👋",
                color = GrayScale900,
                textAlign = TextAlign.Center,
                lineHeight = 9.38.em,
                style = BazarTheme.typography.bodyMedium
            )
            Text(
                text = "Lorem ipsum dolor sit",
                color = Primary500,
                textAlign = TextAlign.Center,
                lineHeight = 5.63.em,
                style = BazarTheme.typography.headlineSmall
            )
            Text(
                text = "Order #2930541",
                color = GrayScale900,
                textAlign = TextAlign.Center,
                lineHeight = 10.em,
                style = BazarTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun OrderDetails(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Order Details",
        color = GrayScale900,
        style = BazarTheme.typography.titleMedium,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier
            .border(
                width = 1.dp, color = GrayScale200, shape = RoundedCornerShape(8.dp)
            )
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OrderList()
        Divider(modifier = Modifier.padding(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Subtotal",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge,
            )
            Text(
                "${paymentDetails.currency} ${paymentDetails.shipping}",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge,
            )
        }
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Shipping",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge,
            )
            Text(
                "${paymentDetails.currency} ${paymentDetails.shipping}",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge,
            )
        }
        Divider(thickness = 1.dp, color = GrayScale200)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Total Payment",
                style = BazarTheme.typography.bodyLarge,
                color = GrayScale900,
            )
            Text(
                "${paymentDetails.currency} ${paymentDetails.total}",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Delivery in",
                style = BazarTheme.typography.bodyLarge,
                color = GrayScale900,
            )
            Text(
                "10 - 15 minus",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge,
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Time",
                style = BazarTheme.typography.bodyLarge,
                color = GrayScale900,
            )
            Text(
                "15.24 - 15.39",
                color = GrayScale900,
                style = BazarTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
fun OrderList(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top), modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.Start)
        ) {
            Text(
                text = "1x",
                color = GrayScale900,
                lineHeight = 10.em,
                style = BazarTheme.typography.bodyMedium,
                modifier = Modifier.requiredWidth(width = 24.dp)
            )
            Text(
                text = "Carrie Fisher",
                color = GrayScale900,
                lineHeight = 10.em,
                style = BazarTheme.typography.bodyMedium,
                modifier = Modifier.requiredWidth(width = 214.dp)
            )
            Text(
                text = "$19.99",
                color = GrayScale900,
                textAlign = TextAlign.End,
                lineHeight = 10.em,
                style = BazarTheme.typography.bodyMedium,
                modifier = Modifier.requiredWidth(width = 55.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.Start)
            ) {
                Text(
                    text = "1x",
                    color = GrayScale900,
                    lineHeight = 10.em,
                    style = BazarTheme.typography.bodyMedium,
                    modifier = Modifier.requiredWidth(width = 24.dp)
                )
                Text(
                    text = "The Da vinci Code",
                    color = GrayScale900,
                    lineHeight = 10.em,
                    style = BazarTheme.typography.bodyMedium,
                    modifier = Modifier.requiredWidth(width = 214.dp)
                )
                Text(
                    text = "$39.99",
                    color = GrayScale900,
                    textAlign = TextAlign.End,
                    lineHeight = 10.em,
                    style = BazarTheme.typography.bodyMedium,
                    modifier = Modifier.requiredWidth(width = 55.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.Start)
        ) {
            Text(
                text = "1x",
                color = GrayScale900,
                lineHeight = 10.em,
                style = BazarTheme.typography.bodyMedium,
                modifier = Modifier.requiredWidth(width = 24.dp)
            )
            Text(
                text = "Arcu ipsum feugiat leo odio ",
                color = GrayScale900,
                lineHeight = 10.em,
                style = BazarTheme.typography.bodyMedium,
                modifier = Modifier.requiredWidth(width = 214.dp)
            )
            Text(
                text = "$27.12",
                color = GrayScale900,
                textAlign = TextAlign.End,
                lineHeight = 10.em,
                style = BazarTheme.typography.bodyMedium,
                modifier = Modifier.requiredWidth(width = 55.dp)
            )
        }
    }
}


@Preview
@Composable
private fun OrderSuccessScreenPreview() {
    OrderSuccessScreen(rememberNavController())
}