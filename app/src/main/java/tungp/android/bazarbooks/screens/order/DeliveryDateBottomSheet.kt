package tungp.android.bazarbooks.screens.order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale200
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.Primary500

data class DeliveryDateItem(
    val id: String,
    val title: String,
    val subTitle: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryDateBottomSheet(
    modifier: Modifier = Modifier,
    dateItems: List<DeliveryDateItem>,
    timeItems: List<DeliveryDateItem>,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    selectedDateId: String? = null,
    selectedTimeId: String? = null,
    onSelectedDate: (String) -> Unit,
    onSelectedTime: (String) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState
    ) {
        DeliveryBottomSheetContainer(
            dateItems,
            selectedDateId,
            onSelectedDate,
            timeItems,
            selectedTimeId,
            onSelectedTime
        )
    }
}

@Composable
private fun DeliveryBottomSheetContainer(
    dateItems: List<DeliveryDateItem>,
    selectedDateId: String?,
    onSelectedDate: (String) -> Unit,
    timeItems: List<DeliveryDateItem>,
    selectedTimeId: String?,
    onSelectedTime: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeliveryDateContent(
            items = dateItems,
            selectedId = selectedDateId,
            onItemSelected = onSelectedDate
        )
        DeliveryTimeContent(
            items = timeItems,
            selectedId = selectedTimeId,
            onItemSelected = onSelectedTime
        )
        PrimaryButton(
            text = "Confirm",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {}
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}

@Composable
fun DeliveryTimeContent(
    items: List<DeliveryDateItem>,
    selectedId: String? = null,
    onItemSelected: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BazarTheme.colors.background,
            )
            .padding(16.dp)
    ) {
        Text(
            "Delivery Time",
            style = BazarTheme.typography.titleLarge,
            color = GrayScale900
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        SingleDeliverySelectionList(
            items = items,
            selectedItemId = selectedId,
            onItemSelected = onItemSelected
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DeliveryDateContent(
    items: List<DeliveryDateItem>,
    selectedId: String? = null,
    onItemSelected: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BazarTheme.colors.background,
            )
            .padding(16.dp)
    ) {
        Text(
            "Delivery Date",
            style = BazarTheme.typography.titleLarge,
            color = GrayScale900
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SingleDeliverySelectionList(
                items = items,
                selectedItemId = selectedId,
                onItemSelected = onItemSelected,
                modifier = Modifier.weight(1f)
            )
            PickDateRow(onItemClick = {}, modifier = Modifier.weight(0.5f))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SingleDeliverySelectionList(
    items: List<DeliveryDateItem>,
    selectedItemId: String?,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            DeliverySelectionRow(
                modifier = Modifier.weight(1f),
                item = item,
                isSelected = item.id == selectedItemId,
                onItemClick = { onItemSelected(item.id) }
            )
        }
    }
}

@Composable
private fun PickDateRow(
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = BazarTheme.colors.background)
            .border(
                border = BorderStroke(1.dp, color = GrayScale200),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clickable { onItemClick() }
    ) {
        Text(
            text = "Pick",
            color = GrayScale900,
            textAlign = TextAlign.Center,
            style = BazarTheme.typography.bodyMedium
        )
        Text(
            text = "a date",
            color = GrayScale900,
            textAlign = TextAlign.Center,
            style = BazarTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun DeliverySelectionRow(
    item: DeliveryDateItem,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = BazarTheme.colors.background)
            .border(
                border = BorderStroke(1.dp, color = if (isSelected) Primary500 else GrayScale200),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clickable { onItemClick() }
    ) {
        Text(
            text = item.title,
            color = GrayScale900,
            textAlign = TextAlign.Center,
            style = BazarTheme.typography.bodyMedium
        )
        Text(
            text = item.subTitle,
            color = GrayScale900,
            textAlign = TextAlign.Center,
            style = BazarTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingleDeliverySelectionListPreview() {

    var dateSelectedId by remember { mutableStateOf<String?>(null) }
    var timeSelectedId by remember { mutableStateOf<String?>(null) }

    BazarTheme {

        DeliveryBottomSheetContainer(
            OrderSampleData.dateItems,
            dateSelectedId,
            {},
            OrderSampleData.timeItems,
            timeSelectedId,
            {})
    }

}