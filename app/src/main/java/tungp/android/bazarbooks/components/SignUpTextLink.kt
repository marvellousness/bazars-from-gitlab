//package tungp.android.bazarbooks.components
//
//import androidx.annotation.StringRes
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.unit.dp
//import tungp.android.bazarbooks.ui.theme.BazarTheme
//import tungp.android.bazarbooks.ui.theme.GrayScale500
//
//@Composable
//fun AuthTextLink(
//    @StringRes text1: Int,
//    @StringRes text2: Int,
//    onClick: () -> Unit,
//) {
//    val annotatedString = buildAnnotatedString {
//        withStyle(SpanStyle(color = GrayScale500)) {
//            append(stringResource(id = text1))
//            append(" ")
//        }
//
//        pushStringAnnotation(tag = "click", annotation = "click")
//        withStyle(
//            SpanStyle(
//                color = BazarTheme.colors.primary,
//                textDecoration = TextDecoration.None
//            )
//        ) {
//            append(stringResource(id = text2))
//        }
//        pop()
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = annotatedString,
//            modifier = Modifier.clickable {
//                val clickAnnotation =
//                    annotatedString.getStringAnnotations(tag = "click", start = 0, end = annotatedString.length)
//                        .firstOrNull()
//                if (clickAnnotation != null) {
//                    onClick()
//                }
//            }
//        )
//    }
//}