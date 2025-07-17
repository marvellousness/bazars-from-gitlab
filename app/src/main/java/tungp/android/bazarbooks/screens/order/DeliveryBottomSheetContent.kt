package tungp.android.bazarbooks.screens.order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale200
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.Primary500
import tungp.android.bazarbooks.ui.theme.constants.MyColors

data class DeliveryItem(
    val id: String,
    val title: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryBottomSheet(
    items: List<DeliveryItem>,
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    selectedId: String? = null,
    onItemSelected: (String) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState
    ) {
        DeliveryBottomSheetContent(
            items = items,
            selectedId = selectedId,
            onItemSelected = onItemSelected
        )
    }
}

@Composable
fun DeliveryBottomSheetContent(
    items: List<DeliveryItem>,
    selectedId: String? = null,
    onItemSelected: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MyColors.surfaceContainerLowest,
            )
            .padding(16.dp)
    ) {
        Text(
            "Your Payments",
            style = BazarTheme.typography.titleLarge,
            color = GrayScale900,
            modifier = Modifier
                .padding(bottom = 17.dp, start = 27.dp)
        )
        SingleDeliverySelectionList(
            items = items,
            selectedItemId = selectedId,
            onItemSelected = onItemSelected
        )


    }
}

@Composable
fun SingleDeliverySelectionList(
    items: List<DeliveryItem>,
    selectedItemId: String?,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            DeliverySelectionRow(
                item = item,
                isSelected = item.id == selectedItemId,
                onItemClick = { onItemSelected(item.id) }
            )
        }
    }
}

@Composable
private fun DeliverySelectionRow(
    item: DeliveryItem,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, color = if (isSelected) Primary500 else GrayScale200),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.width(16.dp))

            // Title
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Selection indicator or arrow
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Select",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SingleDeliverySelectionListPreview() {
    val items = listOf(
        DeliveryItem(
            id = "1",
            title = "Today",
        ),
        DeliveryItem(
            id = "tomorrow",
            title = "Tomorrow",
        )
    )

    var selectedId by remember { mutableStateOf<String?>(null) }

    BazarTheme {
        SingleDeliverySelectionList(
            items = items,
            selectedItemId = selectedId,
            onItemSelected = { selectedId = it }
        )
    }
}