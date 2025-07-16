package tungp.android.bazarbooks.screens.order

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BazarAppBar(
                title = "Confirm Order",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClicked = onNavigationClicked,
                onActionClicked = onActionClicked
            )
        }) { paddingValues ->

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                ConfirmOrderDetails()
//                // Sheet content
//                Button(onClick = {
//                    scope.launch { sheetState.hide() }.invokeOnCompletion {
//                        if (!sheetState.isVisible) {
//                            showBottomSheet = false
//                        }
//                    }
//                }) {
//                    Text("Hide bottom sheet")
//                }
            }
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
            SummarySection(onViewDetailsClicked = {
                showBottomSheet = true
                Log.d("~~~TAG", "View Details clicked")
            })
            DataAndTimeSection()
            PaymentSection()
            PrimaryButton(text = "Order", onClick = { println("Pressed!") })
        }
    }
}

@Composable
fun ConfirmOrderDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MyColors.surfaceContainerLowest,
            )
            .padding(vertical = 16.dp)
    ) {
        Text(
            "Payment Details",
            color = Color(0xFF121212),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 17.dp, start = 27.dp)
        )
        Column(
            modifier = Modifier
                .padding(bottom = 47.dp, start = 24.dp, end = 24.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE8E8E8),
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .padding(vertical = 19.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 17.dp, end = 17.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Price",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
                Text(
                    "$87.10",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 17.dp, end = 17.dp)
                    .fillMaxWidth()
            ) {
                Box {
                    Column(
                        modifier = Modifier
                            .width(74.dp)
                            .padding(start = 1.dp)
                    ) {
                        Text(
                            "Payment",
                            color = Color(0xFF121212),
                            fontSize = 18.sp,
                        )
                    }
                    Text(
                        "Squid Sweet and Sour Salad",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                        modifier = Modifier
                            .offset(x = 1.dp, y = 9.dp)
                            .align(Alignment.TopStart)
                    )
                    Text(
                        "Japan Hainanese Sashimi",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                        modifier = Modifier
                            .offset(x = -1.dp, y = 9.dp)
                            .align(Alignment.BottomStart)
                            .padding(bottom = 9.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .width(35.dp)
                ) {
                    Text(
                        "$19.99",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )
                    Text(
                        "$39.99",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 9.dp, start = 17.dp, end = 17.dp)
                    .fillMaxWidth()
            ) {
                Box {
                    Column(
                        modifier = Modifier
                            .width(104.dp)
                    ) {
                        Text(
                            "Cash on Delivery",
                            color = Color(0xFF121212),
                            fontSize = MyFontSize.body_medium,
                        )
                    }
                    Text(
                        "Black Pepper Beef Lumpia",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                        modifier = Modifier
                            .offset(x = 26.dp, y = 0.dp)
                            .align(Alignment.TopEnd)
                    )
                }
                Text(
                    "$27.12",
                    color = Color(0xFFA5A5A5),
                    fontSize = MyFontSize.body_small,
                )
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 77.dp, end = 77.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "$89.10",
                        color = Color(0xFF7A7A7A),
                        fontSize = MyFontSize.body_medium,
                    )
                }
                Column(
                    modifier = Modifier
                        .offset(x = 61.dp, y = 6.dp)
                        .align(Alignment.TopStart)
                        .padding(bottom = 6.dp)
                        .width(295.dp)
                        .height(1.dp)
                        .background(
                            color = Color(0xFFE8E8E8),
                        )
                ) {
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 17.dp, start = 17.dp, end = 17.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Shipping",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
                Text(
                    "$2",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 19.dp, start = 16.dp, end = 16.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFE8E8E8),
                    )
            ) {
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 17.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Total Payment",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
                Text(
                    "$89.10",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
            }
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
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                    modifier = Modifier.padding(bottom = 7.dp)
                )
                Text(
                    "Dumbo Street No.20, Dumbo, New York 10001, United States",
                    color = Color(0xFFA5A5A5),
                    fontSize = MyFontSize.body_medium,
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
                fontSize = MyFontSize.body_medium,
            )
            Text(
                "$87.10",
                color = GrayScale900,
                fontSize = MyFontSize.body_medium,
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
                fontSize = MyFontSize.body_medium,
            )
            Text(
                "$2",
                color = GrayScale900,
                fontSize = MyFontSize.body_medium,
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
                fontSize = MyFontSize.body_medium,
            )
            Text(
                "$89.10",
                color = GrayScale900,
                fontSize = MyFontSize.body_medium,
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
                fontSize = MyFontSize.body_medium,
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
private fun PaymentSection() {
    OrderCard(title = "Payment") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
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
            color = Color(0xFF121212),
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
            PaymentSection()
        }
    }
}