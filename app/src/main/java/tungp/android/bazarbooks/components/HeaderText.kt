package tungp.android.bazarbooks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale500
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = BazarTheme.typography.titleLarge,
        fontWeight = FontWeight.W800,
        modifier = modifier
    )
}

@Composable
fun SubHeaderText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = BazarTheme.typography.bodyLarge,
        color = GrayScale500,
        modifier = modifier
    )
}

@Preview
@Composable
private fun TextPreview() {
    ThemedPreview {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(text = "Header Section")
            SubHeaderText(text = "Sub Header")
        }
    }
}