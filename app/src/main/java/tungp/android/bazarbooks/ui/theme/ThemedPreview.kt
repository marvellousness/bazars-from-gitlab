package tungp.android.bazarbooks.ui.theme

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    uiMode: UiMode = UiMode.PhonePortrait,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalUiMode provides uiMode) {
        BazarTheme(darkTheme = darkTheme) {
            Surface(color = BazarTheme.colors.surface) {
                content()
            }
        }
    }
}
