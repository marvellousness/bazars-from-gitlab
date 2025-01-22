package tungp.android.bazarbooks.ui.theme

import androidx.compose.runtime.Composable

@Composable
internal fun BazarPreviewTheme(content: @Composable () -> Unit) {
    BazarTheme {
        content.invoke()
    }
}