package tungp.android.bazarbooks.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun OrWithHorizontalDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = BazarTheme.colors.outlineVariant,
            thickness = 1.dp
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = "Or with",
            color = BazarTheme.colors.outlineVariant,
            style = BazarTheme.typography.bodyMedium,
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = BazarTheme.colors.outlineVariant,
            thickness = 1.dp
        )
    }
}

@Preview
@Composable
private fun OrWithHorizontalLinePreview() {
    OrWithHorizontalDivider()
}