package tungp.android.bazarbooks.screens.auth.otpverification

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationCodeInput(
    code: List<Char?>,
    onCodeChange: (List<Char?>) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequesters = remember { List(4) { FocusRequester() } }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 0 until 4) {
            OutlinedTextField(
                value = code.getOrNull(i)?.toString() ?: "",
                onValueChange = {
                    val newCode = code.toMutableList()
                    if (it.isNotEmpty()) {
                        newCode[i] = it.last()
                        if (i < 3) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    } else {
                        newCode[i] = null
                    }
                    onCodeChange(newCode)
                },
                modifier = Modifier
                    .width(60.dp)
                    .focusRequester(focusRequesters[i]),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(300)
        focusRequesters[0].requestFocus()
    }
}