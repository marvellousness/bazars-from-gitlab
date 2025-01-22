package tungp.android.bazarbooks.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun EmptyView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Default.HourglassEmpty),
            contentDescription = null,
            tint = Red,
            modifier = modifier
        )
        Text(
            text = stringResource(id = R.string.text_no_data_found),
            style = BazarTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun EmptyPageViewPreview() {
    BazarTheme {
        EmptyView()
    }
}