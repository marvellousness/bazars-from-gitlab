package tungp.android.bazarbooks.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = BazarTheme.typography.headlineSmall,
        fontWeight = FontWeight.W800,
        modifier = modifier
    )
}

@Preview
@Composable
private fun HeaderTextPreview() {
    ThemedPreview {
        HeaderText(text = "Header")
    }
}