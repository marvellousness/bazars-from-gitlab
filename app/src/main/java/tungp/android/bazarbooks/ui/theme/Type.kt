package tungp.android.bazarbooks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = OpenSansFonts,
        fontWeight = FontWeight.Light,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.5.sp
    ), displayMedium = TextStyle(
        fontFamily = OpenSansFonts,
        fontWeight = FontWeight.Light,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.5.sp
    ), displaySmall = TextStyle(
        fontFamily = OpenSansFonts,
        fontWeight = FontWeight.W700,
        fontSize = 36.0.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.5.sp
    ), headlineLarge = TextStyle(
        fontFamily = OpenSansFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.5.sp
    ), headlineMedium = TextStyle(
        fontFamily = OpenSansFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.5.sp
    ), headlineSmall = TextStyle(
        fontFamily = OpenSansFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ), titleLarge = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ), titleMedium = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ), titleSmall = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ), bodyLarge = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.W400,
        fontSize = 16.0.sp,
        lineHeight = 24.0.sp,
        letterSpacing = 0.5.sp
    ), bodyMedium = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ), bodySmall = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ), labelLarge = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ), labelMedium = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ), labelSmall = TextStyle(
        fontFamily = RobotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

internal val LocalBazarTypography = staticCompositionLocalOf { Typography }