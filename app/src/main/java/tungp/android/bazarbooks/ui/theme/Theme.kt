package tungp.android.bazarbooks.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun BazarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: Typography = BazarTheme.typography,
    shapes: Shapes = BazarTheme.shapes,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val extendedColorScheme = when {
        darkTheme -> defaultExtendedDarkColorScheme
        else -> defaultExtendedLightColorScheme
    }

    CompositionLocalProvider(
        LocalBazarColors provides colorScheme,
        LocalBazarTypography provides typography,
        LocalBazarShapes provides shapes,
        LocalExtendedColors provides extendedColorScheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

object BazarTheme {

    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalBazarColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalBazarTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalBazarShapes.current

    val spacing: BazarSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBazarSpacing.current
        
    val extendedColors: ExtendedColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalExtendedColors.current
}