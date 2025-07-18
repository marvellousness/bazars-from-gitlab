package tungp.android.bazarbooks.screens.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.Divider
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressDetailsBottomSheet(
    addressDetails: AddressDetails,
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    selectedOffice: String,
    onOfficeSelected: (String) -> Unit,
    onConfirmed: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        }, sheetState = sheetState
    ) {
        AddressDetailCard(
            modifier = Modifier,
            addressDetails = addressDetails,
            selectedOffice = selectedOffice,
            onOfficeSelected = onOfficeSelected,
            onConfirmed = onConfirmed
        )
    }
}

@Composable
fun AddressDetailCard(
    modifier: Modifier = Modifier,
    addressDetails: AddressDetails,
    selectedOffice: String,
    onOfficeSelected: (String) -> Unit,
    onConfirmed: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = BazarTheme.colors.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Payment Details", style = BazarTheme.typography.titleLarge, color = GrayScale900
            )
            Image(
                painter = painterResource(id = R.drawable.localtion),
                contentDescription = "location",
                modifier = Modifier.requiredSize(size = 24.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFF6B46C1), shape = CircleShape
                    ), contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ography_location_outline),
                    contentDescription = "Location",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = addressDetails.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = addressDetails.subTitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp), thickness = 1.dp, Color.LightGray)
        Text(
            "Save Address As", style = BazarTheme.typography.titleLarge, color = GrayScale900
        )
        Spacer(modifier = Modifier.height(16.dp))
        OfficeSelector(
            items = OrderSampleData.officeAddresses,
            selectedOffice = selectedOffice,
            onOfficeSelected = onOfficeSelected
        )
        Spacer(modifier = Modifier.height(40.dp))
        PrimaryButton(
            text = "Confirm",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onConfirmed
        )
    }
}

@Composable
fun OfficeSelector(
    items: List<String>,
    selectedOffice: String,
    onOfficeSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            FilterChip(
                selected = item == selectedOffice,
                onClick = { onOfficeSelected(item) },
                label = { Text(item) })
        }
    }
}


@Preview
@Composable
private fun AddressDetailCardPreview() {
    AddressDetailCard(
        modifier = Modifier,
        addressDetails = OrderSampleData.addressDetails,
        selectedOffice = OrderSampleData.officeAddresses[0],
        onOfficeSelected = {},
        onConfirmed = {}
    )
}