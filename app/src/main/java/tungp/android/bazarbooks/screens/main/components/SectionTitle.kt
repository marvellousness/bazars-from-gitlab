package tungp.android.bazarbooks.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.LocalBazarSpacing
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@Composable
fun SectionTitle(
    title: String,
    onSeeAll: (Int) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalBazarSpacing.current.medium,
                vertical = LocalBazarSpacing.current.extraMedium
            )
    ) {
        Text(
            text = title,
            style = BazarTheme.typography.titleMedium,
            color = Color(0xFF121212),
            fontSize = 18.sp,
        )
        Text(
            "See all",
            color = Color(0xFF54408C),
            style = BazarTheme.typography.titleSmall,
            modifier = Modifier.clickable {
                onSeeAll(1)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSectionTitle() {
    ThemedPreview {
        SectionTitle("Title") { id -> }
    }
}