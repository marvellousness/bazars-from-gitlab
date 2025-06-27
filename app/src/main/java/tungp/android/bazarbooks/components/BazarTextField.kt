package tungp.android.bazarbooks.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun BazarTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeHolderResourceId: Int,
    @DrawableRes iconResourceId: Int? = null,
    labelText: String,
    placeholder: @Composable (() -> Unit) = {
        Text(
            text = stringResource(id = placeHolderResourceId),
            color = BazarTheme.colors.outlineVariant
        )
    },
    trailingIcon: @Composable (() -> Unit) = {
        iconResourceId?.let {
            BazarIcon(
                iconResourceId = it,
                contentDescription = stringResource(id = placeHolderResourceId)
            )
        }
    },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    error: String? = null,
    onBlur: () -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = labelText, style = BazarTheme.typography.bodyMedium)
        OutlinedTextField(
            modifier = modifier
                .onFocusChanged {
                    if (isFocused && !it.isFocused) {
                        onBlur()
                        keyboardController?.hide()
                    }
                    isFocused = it.isFocused
                },
            value = value,
            onValueChange = onValueChange,
            trailingIcon = trailingIcon,
            placeholder = placeholder,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            shape = BazarTheme.shapes.medium,
            isError = error != null,
            supportingText = {
                error?.let {
                    Text(text = error)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevTextField() {
    BazarTextField(
        value = "",
        onValueChange = {},
        labelText = "Email",
        placeHolderResourceId = R.string.email_placeholder,
        iconResourceId = R.drawable.ic_ography_password_outline
    )
}

@Preview(showBackground = true)
@Composable
fun PrevTextFieldTrailingIcon() {
    BazarTextField(
        value = "",
        onValueChange = {},
        labelText = "Password",
        placeHolderResourceId = R.string.email_placeholder,
        iconResourceId = R.drawable.ic_ography_password_outline
    )
}