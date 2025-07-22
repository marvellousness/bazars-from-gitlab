package tungp.android.bazarbooks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale900

@Composable
fun QuantityPicker(
    modifier: Modifier = Modifier,
    initialAmount: Int = 1,
    maxAmount: Int = Int.MAX_VALUE,
    onAmountChanged: (Int) -> Unit,
) {
    var amount = remember { mutableIntStateOf(initialAmount.coerceIn(1, maxAmount)) }
    val isDecrementEnabled = amount.intValue > 1
    val isIncrementEnabled = amount.intValue < maxAmount

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                color = BazarTheme.colors.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            tint = if (isDecrementEnabled) Color.White else BazarTheme.colors.primary,
            imageVector = Icons.Default.Remove,
            contentDescription = "Decrease amount",
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 50))
                .background(if (isDecrementEnabled) BazarTheme.colors.primary else BazarTheme.colors.surfaceDim)
                .width(24.dp)
                .height(24.dp)
                .clickable(enabled = isDecrementEnabled) {
                    if (amount.intValue > 1) {
                        amount.intValue--
                        onAmountChanged(amount.intValue)
                    }
                }
        )
        Text(
            color = GrayScale900,
            text = amount.intValue.toString(),
            style = BazarTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center
        )
        Icon(
            tint = BazarTheme.colors.surface,
            imageVector = Icons.Default.Add,
            contentDescription = "Increase amount",
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 50))
                .background(if (isIncrementEnabled) BazarTheme.colors.primary else BazarTheme.colors.surfaceDim)
                .width(24.dp)
                .height(24.dp)
                .clickable(enabled = isIncrementEnabled) {
                    if (amount.intValue < maxAmount) {
                        amount.intValue++
                        onAmountChanged(amount.intValue)
                    }
                }
        )
    }
}

@Preview
@Composable
fun AmountComponentPreview() {
    QuantityPicker(
        initialAmount = 1, maxAmount = 10, onAmountChanged = {})
}