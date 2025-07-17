package tungp.android.bazarbooks.screens.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BazarAppBar
import tungp.android.bazarbooks.components.Divider
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.components.button.SecondaryButton
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale200
import tungp.android.bazarbooks.ui.theme.GrayScale600
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.Primary500
import tungp.android.bazarbooks.ui.theme.constants.MyColors
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun OrderScreen(
    rootNavController: NavController,
    viewModel: OrderViewModel = hiltViewModel(),
) {
    OrderContainer(viewModel = viewModel, onNavigationClicked = { }, onActionClicked = { })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderContainer(
    viewModel: OrderViewModel,
    onNavigationClicked: () -> Unit,
    onActionClicked: () -> Unit,
) {

    val paymentDetailsSheetState = rememberModalBottomSheetState()
    var showPaymentDetailsSheet by remember { mutableStateOf(false) }

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

    val paymentMethodSheetState = rememberModalBottomSheetState()
    var showPaymentMethodSheet by remember { mutableStateOf(false) }
    var paymentMethodSelectedItem by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BazarAppBar(
                title = "Confirm Order",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClicked = onNavigationClicked,
                onActionClicked = onActionClicked
            )
        }) { paddingValues ->

        if (showPaymentDetailsSheet) {
            PaymentDetailBottomSheet(
                data = "sample data",
                sheetState = paymentDetailsSheetState,
                onDismissRequest = { showPaymentDetailsSheet = false }
            )
        }

        if (showPaymentMethodSheet) {
            YourPaymentsBottomSheet(
                items = paymentMethodItems,
                modifier = Modifier,
                sheetState = paymentMethodSheetState,
                onDismissRequest = { showPaymentMethodSheet = false },
                selectedId = paymentMethodSelectedItem,
                onItemSelected = {
                    paymentMethodSelectedItem = it
                })
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = MyColors.surfaceContainerLowest,
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddressSection()
            SummarySection {
                showPaymentDetailsSheet = true
            }
            DataAndTimeSection()
            PaymentSection {
                showPaymentMethodSheet = true
            }
            PrimaryButton(
                text = "Order", onClick = { println("Pressed!") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun AddressSection() {
    OrderCard(title = "Address") {
        Row(
            modifier = Modifier
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            CoilImage(
                imageModel = { R.drawable.ic_ography_location_location },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(end = 17.dp)
                    .width(44.dp)
                    .height(44.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 5.dp, end = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    "Utama Street No.20",
                    color = GrayScale900,
                    fontSize = MyFontSize.body_large,
                    modifier = Modifier.padding(bottom = 7.dp)
                )
                Text(
                    "Dumbo Street No.20, Dumbo, New York 10001, United States",
                    color = Color(0xFFA5A5A5),
                    style = BazarTheme.typography.bodyMedium,
                )
            }
            CoilImage(
                imageModel = { R.drawable.ic_ography_chevron_right },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(top = 24.dp)
                    .width(7.dp)
                    .height(14.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            SecondaryButton(
                text = "Change", onClick = {}, modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Composable
private fun SummarySection(onViewDetailsClicked: () -> Unit) {
    OrderCard(title = "Summary") {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 13.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Price",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
            Text(
                "$87.10",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Shipping",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
            Text(
                "$2",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 1.dp, color = BazarTheme.colors.secondary)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Total Payment",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
            Text(
                "$89.10",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
        }
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(
                    color = BazarTheme.colors.secondary,
                )
        ) {}
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "See details",
                style = BazarTheme.typography.bodyMedium,
                color = Primary500,
                modifier = Modifier
                    .padding(end = 11.dp)
                    .clickable(onClick = onViewDetailsClicked)
            )
            CoilImage(
                imageModel = { R.drawable.ic_ography_chevron_right },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .width(4.dp)
                    .height(9.dp)
            )
        }
    }
}

@Composable
private fun DataAndTimeSection() {
    OrderCard(title = "Date and time") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            CoilImage(
                imageModel = { R.drawable.ic_ography_calendar },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(44.dp)
                    .height(44.dp)
            )
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Date & time",
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "Choose date and time ",
                    style = BazarTheme.typography.bodyMedium,
                    color = GrayScale600
                )
            }
            CoilImage(
                imageModel = { R.drawable.ic_ography_chevron_right },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .width(7.dp)
                    .height(14.dp)
            )
        }
    }
}

@Composable
private fun PaymentSection(onPaymentMethodSelected: () -> Unit){
    OrderCard(title = "Payment") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                .clickable { onPaymentMethodSelected() }
        ) {
            CoilImage(
                imageModel = { R.drawable.ic_ography_card_outline },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(44.dp)
                    .height(44.dp)
            )
            Column(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    "Payment",
                    style = BazarTheme.typography.bodyMedium,
                    color = GrayScale900,
                    modifier = Modifier.padding(bottom = 9.dp)
                )
                Text(
                    "Choose your payment",
                    style = BazarTheme.typography.bodyMedium,
                    color = GrayScale600,
                )
            }
            CoilImage(
                imageModel = { R.drawable.ic_ography_chevron_right },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .width(7.dp)
                    .height(14.dp)
            )
        }
    }
}

@Composable
fun OrderCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
            .border(
                width = 1.dp, color = GrayScale200, shape = RoundedCornerShape(8.dp)
            )
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(vertical = 21.dp)
    ) {
        Text(
            text = title,
            color = GrayScale900,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 11.dp, start = 16.dp)
        )
        content()
    }
}

@Preview
@Composable
private fun OrderScreenPreview() {
    BazarTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MyColors.surfaceContainerLowest,
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddressSection()
            SummarySection(onViewDetailsClicked = {})
            DataAndTimeSection()
            PaymentSection {}
        }
    }
}