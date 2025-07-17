package tungp.android.bazarbooks.screens.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.components.Divider
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale200
import tungp.android.bazarbooks.ui.theme.GrayScale600
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.constants.MyColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentDetailBottomSheet(
    data: String,
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState
    ) {
        PaymentDetailsBottomSheetContent()
    }
}

@Composable
fun PaymentDetailsBottomSheetContent() {
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
            style = BazarTheme.typography.titleLarge,
            color = GrayScale900,
            modifier = Modifier
                .padding(bottom = 17.dp, start = 27.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .border(
                    width = 1.dp,
                    color = GrayScale200,
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
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyLarge,
                )
                Text(
                    "$87.10",
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyLarge,
                )
            }


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 17.dp, end = 17.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Squid Sweet and Sour Salad",
                    color = GrayScale600,
                    style = BazarTheme.typography.bodySmall,
                )
                Text(
                    "$19.99",
                    color = GrayScale600,
                    style = BazarTheme.typography.bodySmall,
                )
            }


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 17.dp, end = 17.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Japan Hainanese Sashimi",
                    color = GrayScale600,
                    style = BazarTheme.typography.bodySmall,
                )
                Text(
                    "$39.99",
                    color = GrayScale600,
                    style = BazarTheme.typography.bodySmall,
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
                    "Black Pepper Beef Lumpia",
                    color = GrayScale600,
                    style = BazarTheme.typography.bodySmall,
                )
                Text(
                    "$27.12",
                    color = GrayScale600,
                    style = BazarTheme.typography.bodySmall,
                )
            }
            Divider(thickness = 1.dp, color = GrayScale200)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 17.dp, start = 17.dp, end = 17.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Shipping",
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyLarge,
                )
                Text(
                    "$2",
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyLarge,
                )
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 19.dp, start = 16.dp, end = 16.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = GrayScale200)
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
                    style = BazarTheme.typography.bodyLarge,
                    color = GrayScale900,
                )
                Text(
                    "$89.10",
                    color = GrayScale900,
                    style = BazarTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ConfirmOrderDetailsPreview() {
    PaymentDetailsBottomSheetContent()
}