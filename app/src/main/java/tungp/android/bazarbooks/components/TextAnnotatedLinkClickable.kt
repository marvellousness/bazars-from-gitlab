package tungp.android.bazarbooks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale500

@Composable
fun TextAnnotatedLinkClickable(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String,
    styleText1: SpanStyle = SpanStyle(color = GrayScale500),
    styleText2: SpanStyle = SpanStyle(color = BazarTheme.colors.primary),
    centerAligned: Boolean = false,
    onClick: () -> Unit,
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = if (centerAligned)
                ParagraphStyle(textAlign = TextAlign.Center)
            else
                ParagraphStyle(textAlign = TextAlign.Left)

        ) {
            withStyle(style = styleText1) {
                append(text1)
                append(" ")
            }

            pushStringAnnotation(tag = "click", annotation = "click")
            withStyle(style = styleText2) {
                append(text2)
            }
            pop()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = annotatedString,
            modifier = Modifier.clickable {
                val clickAnnotation =
                    annotatedString.getStringAnnotations(
                        tag = "click",
                        start = 0,
                        end = annotatedString.length
                    )
                        .firstOrNull()
                if (clickAnnotation != null) {
                    onClick()
                }
            }
        )
    }
}