package tungp.android.bazarbooks.ui.theme

import androidx.compose.runtime.Composable

@Composable
internal fun PreviewTheme(content: @Composable () -> Unit) {
    BazarTheme {
        content.invoke()
    }
}