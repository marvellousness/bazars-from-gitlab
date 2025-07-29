package tungp.android.bazarbooks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale500

@Composable
fun SignInTextLink(
    onClick: () -> Unit,
) {
    val text = buildAnnotatedString {
        withStyle(SpanStyle(color = GrayScale500)) {
            append(stringResource(id = R.string.dont_have_an_account))
            append(" ")
        }

        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                color = BazarTheme.colors.primary,
                textDecoration = TextDecoration.None
            )
        ) {
            append(stringResource(id = R.string.sign_up))
        }
        pop()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.clickable {
                val clickAnnotation =
                    text.getStringAnnotations(tag = "click", start = 0, end = text.length)
                        .firstOrNull()
                if (clickAnnotation != null) {
                    onClick()
                }
            }
        )
    }
}