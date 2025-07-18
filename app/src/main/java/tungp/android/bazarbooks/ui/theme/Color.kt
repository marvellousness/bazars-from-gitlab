package tungp.android.bazarbooks.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// regin Primary colors
val Primary50 = Color(0xFFFAF9FD)
val Primary100 = Color(0xFFE5DEF8)
val Primary200 = Color(0xFFCABCEF)
val Primary300 = Color(0xFFA28CE0)
val Primary400 = Color(0xFF7D64C3)
val Primary500 = Color(0xFF54408C)
val Primary600 = Color(0xFF352368)
val Primary700 = Color(0xFF251554)
val Primary800 = Color(0xFF10052F)
val Primary900 = Color(0xFF09031B)
// endregion

// region GreyScale colors
val GrayScale50 = Color(0xFFFAFAFA)
val GrayScale100 = Color(0xFFF5F5F5)
val GrayScale200 = Color(0xFFE8E8E8)
val GrayScale300 = Color(0xFFD6D6D6)
val GrayScale400 = Color(0xFFB8B8B8)
val GrayScale500 = Color(0xFFA6A6A6)
val GrayScale600 = Color(0xFF7A7A7A)
val GrayScale700 = Color(0xFF454545)
val GrayScale800 = Color(0xFF292929)
val GrayScale900 = Color(0xFF121212)
// endregion

internal val DarkColorScheme = darkColorScheme(
    primary = Primary200,
    secondary = GrayScale200,
    tertiary = Primary200
)

internal val LightColorScheme = lightColorScheme(
    primary = Primary500,
    secondary = GrayScale200,
    tertiary = Primary200,
    outline = GrayScale200,
    background = Color(0xFFFFFFFF),
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

internal val LocalBazarColors = staticCompositionLocalOf { LightColorScheme }