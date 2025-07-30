package tungp.android.bazarbooks.screens.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BackNavigationAction
import tungp.android.bazarbooks.components.BazarAppBar
import tungp.android.bazarbooks.components.Divider
import tungp.android.bazarbooks.components.HeaderText
import tungp.android.bazarbooks.components.NotificationAction
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.components.button.SecondaryButton
import tungp.android.bazarbooks.navigation.CartRouteScreen
import tungp.android.bazarbooks.screens.order.bottomsheet.AddressDetailsBottomSheet
import tungp.android.bazarbooks.screens.order.bottomsheet.DeliveryDateBottomSheet
import tungp.android.bazarbooks.screens.order.bottomsheet.PaymentDetailBottomSheet
import tungp.android.bazarbooks.screens.order.bottomsheet.YourPaymentsBottomSheet
import tungp.android.bazarbooks.screens.order.model.PaymentDetails
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale200
import tungp.android.bazarbooks.ui.theme.GrayScale600
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.Primary500

@Composable
fun OrderScreen(
    rootNavController: NavController,
    viewModel: OrderViewModel = hiltViewModel(),
) {
    OrderContainer(
        viewModel = viewModel,
        onNavigationClicked = {
            rootNavController.popBackStack()
        },
        onActionClicked = { },
        onLocationChanged = {
            rootNavController.navigate(CartRouteScreen.LocationDetails.route)
        },
        onOrderClicked = {
            rootNavController.navigate(CartRouteScreen.OrderSuccess.route)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderContainer(
    viewModel: OrderViewModel,
    onNavigationClicked: () -> Unit,
    onActionClicked: () -> Unit,
    onLocationChanged: () -> Unit,
    onOrderClicked: () -> Unit,
) {

    // For Payment details
    val paymentDetailsSheetState = rememberModalBottomSheetState()
    var showPaymentDetailsSheet by remember { mutableStateOf(false) }

    // For Payment method selection
    val paymentMethodSheetState = rememberModalBottomSheetState()
    var showPaymentMethodSheet by remember { mutableStateOf(false) }
    var paymentMethodSelectedItem by remember { mutableStateOf("") }

    // For Date & Time selection
    val dateTimeSheetState = rememberModalBottomSheetState()
    var showDateTimeSheet by remember { mutableStateOf(false) }
    var dateSelectedId by remember { mutableStateOf("") }
    var timeSelectedId by remember { mutableStateOf("") }

    // For Payment method selection
    val addressDetailsSheetState = rememberModalBottomSheetState()
    var showAddressDetailsSheet by remember { mutableStateOf(false) }
    var addressDetailsSelectedItem by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BazarAppBar(
                title = { HeaderText(text = "Confirm Order") },
                navigationIcon = { BackNavigationAction(onClick = onNavigationClicked) },
                actions = { NotificationAction(onClick = onActionClicked) }
            )
        }) { paddingValues ->

        if (showPaymentDetailsSheet) {
            PaymentDetailBottomSheet(
                paymentDetails = viewModel.paymentDetails,
                sheetState = paymentDetailsSheetState,
                onDismissRequest = { showPaymentDetailsSheet = false }
            )
        }

        if (showPaymentMethodSheet) {
            YourPaymentsBottomSheet(
                items = viewModel.paymentMethodItems,
                modifier = Modifier,
                sheetState = paymentMethodSheetState,
                onDismissRequest = { showPaymentMethodSheet = false },
                selectedId = paymentMethodSelectedItem,
                onItemSelected = {
                    paymentMethodSelectedItem = it
                })
        }

        if (showDateTimeSheet) {
            DeliveryDateBottomSheet(
                sheetState = dateTimeSheetState,
                onDismissRequest = { showDateTimeSheet = false },
                dateItems = OrderSampleData.dateItems,
                timeItems = OrderSampleData.timeItems,
                selectedDateId = dateSelectedId,
                selectedTimeId = timeSelectedId,
                onSelectedDate = {
                    dateSelectedId = it
                },
                onSelectedTime = {
                    timeSelectedId = it
                },
                onConfirmed = {
                    showDateTimeSheet = false
                }
            )
        }

        if (showAddressDetailsSheet) {
            AddressDetailsBottomSheet(
                addressDetails = OrderSampleData.addressDetails,
                sheetState = addressDetailsSheetState,
                onDismissRequest = { showAddressDetailsSheet = false },
                selectedOffice = addressDetailsSelectedItem,
                onOfficeSelected = {
                    addressDetailsSelectedItem = it
                },
                onConfirmed = { showAddressDetailsSheet = false }
            )
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = BazarTheme.colors.background)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddressSection {
                showAddressDetailsSheet = true
                // TODO: Navigate to location screen instead
                //onLocationChanged()
            }
            SummarySection(viewModel.paymentDetails) {
                showPaymentDetailsSheet = true
            }
            DataAndTimeSection {
                showDateTimeSheet = true
            }
            PaymentSection {
                showPaymentMethodSheet = true
            }
            PrimaryButton(
                text = "Order",
                onClick = onOrderClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun AddressSection(onViewDetailsClicked: () -> Unit) {
    OrderCard(title = "Address") {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(44.dp)
                    .height(44.dp)
                    .background(
                        color = BazarTheme.colors.surface, shape = CircleShape
                    ), contentAlignment = Alignment.Center
            ) {
                CoilImage(
                    imageModel = { R.drawable.ic_ography_location_location },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 5.dp, end = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    "Utama Street No.20",
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
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
                text = "Change", onClick = onViewDetailsClicked, modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Composable
private fun SummarySection(
    paymentDetails: PaymentDetails,
    onViewDetailsClicked: () -> Unit,
) {
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
                "${paymentDetails.currency} ${paymentDetails.price}",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
        }
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
                style = BazarTheme.typography.bodyMedium,
            )
            Text(
                "${paymentDetails.currency} ${paymentDetails.shipping}",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium,
            )
        }
        Divider()
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
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                "${paymentDetails.currency} ${paymentDetails.total}",
                color = GrayScale900,
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        Divider(modifier = Modifier.padding(horizontal = 0.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "See details",
                style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Primary500,
                modifier = Modifier
                    .padding(end = 12.dp)
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
private fun DataAndTimeSection(onViewDetailsClicked: () -> Unit) {
    OrderCard(title = "Date and time") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                .clickable { onViewDetailsClicked() }
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(44.dp)
                    .height(44.dp)
                    .background(
                        color = BazarTheme.colors.surface, shape = CircleShape
                    ), contentAlignment = Alignment.Center
            ) {
                CoilImage(
                    imageModel = { R.drawable.ic_ography_calendar },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Date & time",
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "Choose date and time",
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
private fun PaymentSection(onPaymentMethodSelected: () -> Unit) {
    OrderCard(title = "Payment") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                .clickable { onPaymentMethodSelected() }
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(44.dp)
                    .height(44.dp)
                    .background(
                        color = BazarTheme.colors.surface, shape = CircleShape
                    ), contentAlignment = Alignment.Center
            ) {
                CoilImage(
                    imageModel = { R.drawable.ic_ography_card_outline },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    "Payment",
                    style = BazarTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
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
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(8.dp)
            .border(
                width = 1.dp, color = GrayScale200, shape = RoundedCornerShape(8.dp)
            )
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            color = GrayScale900,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 12.dp, start = 16.dp)
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
                .background(color = BazarTheme.colors.background)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddressSection(onViewDetailsClicked = {})
            SummarySection(OrderSampleData.paymentDetails, onViewDetailsClicked = {})
            DataAndTimeSection(
                onViewDetailsClicked = {}
            )
            PaymentSection {}
        }
    }
}