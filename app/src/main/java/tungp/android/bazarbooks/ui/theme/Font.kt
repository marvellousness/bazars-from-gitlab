package tungp.android.bazarbooks.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import tungp.android.bazarbooks.R

val RobotoFonts: FontFamily = FontFamily(
    Font(R.font.roboto_bold, weight = FontWeight.W700, style = FontStyle.Normal),
    Font(R.font.roboto_medium, weight = FontWeight.W500, style = FontStyle.Normal),
    Font(R.font.roboto_regular, weight = FontWeight.W400, style = FontStyle.Normal),
)
val OpenSansFonts: FontFamily = FontFamily(
    Font(R.font.open_sans_bold, weight = FontWeight.W700, style = FontStyle.Normal),
    Font(R.font.open_sans_bold_italic, weight = FontWeight.W700, style = FontStyle.Italic),
    Font(R.font.open_sans_extrabold, weight = FontWeight.W800, style = FontStyle.Normal),
    Font(R.font.open_sans_extrabold_italic, weight = FontWeight.W800, style = FontStyle.Italic),
    Font(R.font.open_sans_italic, weight = FontWeight.W400, style = FontStyle.Italic),
    Font(R.font.open_sans_light, weight = FontWeight.W300, style = FontStyle.Normal),
    Font(R.font.open_sans_light_italic, weight = FontWeight.W300, style = FontStyle.Italic),
    Font(R.font.open_sans_medium, weight = FontWeight.W500, style = FontStyle.Normal),
    Font(R.font.open_sans_medium_italic, weight = FontWeight.W500, style = FontStyle.Italic),
    Font(R.font.open_sans_regular, weight = FontWeight.W400, style = FontStyle.Normal),
    Font(R.font.open_sans_semibold, weight = FontWeight.W600, style = FontStyle.Normal),
    Font(R.font.open_sans_semibold_italic, weight = FontWeight.W600, style = FontStyle.Italic)
)