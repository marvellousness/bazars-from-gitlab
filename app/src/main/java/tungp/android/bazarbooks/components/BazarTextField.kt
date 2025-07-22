package tungp.android.bazarbooks.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
    error: String? = null,
    onBlur: () -> Unit = {},
    isSecure: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Password visibility toggle state
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransform =
        if (isSecure && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = labelText, style = BazarTheme.typography.bodyMedium)
        OutlinedTextField(
            modifier = modifier
                .onFocusChanged { focusState ->
                    onBlur()
                    isFocused = focusState.isFocused
                },
            value = value,
            onValueChange = onValueChange,
            trailingIcon = if (isSecure) {
                {
                    BazarIcon(
                        iconResourceId = if (passwordVisible) R.drawable.ic_ography_unpassword_outline else R.drawable.ic_ography_password_outline,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )
                }
            } else trailingIcon,
            placeholder = placeholder,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransform,
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