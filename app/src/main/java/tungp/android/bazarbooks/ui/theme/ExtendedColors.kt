package tungp.android.bazarbooks.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Yellow color family
val Yellow50 = Color(0xFFFFFDE7)
val Yellow100 = Color(0xFFFFF9C4)
val Yellow200 = Color(0xFFFFF59D)
val Yellow300 = Color(0xFFFFF176)
val Yellow400 = Color(0xFFFFEE58)
val Yellow500 = Color(0xFFFFEB3B)
val Yellow600 = Color(0xFFFDD835)
val Yellow700 = Color(0xFFFBC02D)
val Yellow800 = Color(0xFFF9A825)
val Yellow900 = Color(0xFFF57F17)

// Green color family
val Green50 = Color(0xFFE8F5E9)
val Green100 = Color(0xFFC8E6C9)
val Green200 = Color(0xFFA5D6A7)
val Green300 = Color(0xFF81C784)
val Green400 = Color(0xFF66BB6A)
val Green500 = Color(0xFF4CAF50)
val Green600 = Color(0xFF43A047)
val Green700 = Color(0xFF388E3C)
val Green800 = Color(0xFF2E7D32)
val Green900 = Color(0xFF1B5E20)

/**
 * Extension class for Material ColorScheme that adds yellow and green color families
 */
@Immutable
data class ExtendedColorScheme(
    // Yellow colors
    val yellow: Color,
    val onYellow: Color,
    val yellowContainer: Color,
    val onYellowContainer: Color,
    
    // Green colors
    val green: Color,
    val onGreen: Color,
    val greenContainer: Color,
    val onGreenContainer: Color
)

/**
 * Default implementation of ExtendedColorScheme with light theme values
 */
val defaultExtendedLightColorScheme = ExtendedColorScheme(
    yellow = Yellow700,
    onYellow = Color.Black,
    yellowContainer = Yellow100,
    onYellowContainer = Yellow900,
    
    green = Green500,
    onGreen = Color.White,
    greenContainer = Green100,
    onGreenContainer = Green900
)

/**
 * Default implementation of ExtendedColorScheme with dark theme values
 */
val defaultExtendedDarkColorScheme = ExtendedColorScheme(
    yellow = Yellow700,
    onYellow = Color.Black,
    yellowContainer = Yellow800,
    onYellowContainer = Yellow100,
    
    green = Green300,
    onGreen = Color.Black,
    greenContainer = Green800,
    onGreenContainer = Green100
)

/**
 * CompositionLocal to provide extended colors
 */
val LocalExtendedColors = staticCompositionLocalOf { defaultExtendedLightColorScheme } 