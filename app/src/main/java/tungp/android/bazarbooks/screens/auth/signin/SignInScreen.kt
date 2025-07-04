package tungp.android.bazarbooks.screens.auth.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BazarTextField
import tungp.android.bazarbooks.components.HeaderText
import tungp.android.bazarbooks.components.OrWithHorizontalDivider
import tungp.android.bazarbooks.components.SignInTextLink
import tungp.android.bazarbooks.components.button.BazarBackButton
import tungp.android.bazarbooks.components.button.BazarTextButton
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.components.button.SignInButton
import tungp.android.bazarbooks.ui.theme.BazarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current
    val scrollableState = rememberScrollState()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    BazarBackButton(onClick = { })
                })
        },
        modifier = Modifier
            .clickable(
                onClick = { focusManager.clearFocus() },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(state = scrollableState)
                .fillMaxSize()
        ) {
            HeaderText(
                text = stringResource(R.string.login),
                modifier = Modifier
                    .padding(vertical = BazarTheme.spacing.medium)
                    .align(alignment = Alignment.Start)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.sign_to_your_account),
                style = BazarTheme.typography.bodyLarge,
                color = Color(0xFFA5A5A5)
            )
            Spacer(modifier = Modifier.padding(BazarTheme.spacing.medium))
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    BazarTheme.spacing.medium,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                BazarTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email ?: "",
                    onValueChange = {
                        viewModel.onTriggerEvent(SignInEvent.EmailChanged(it))
                    },
                    labelText = stringResource(R.string.email),
                    placeHolderResourceId = R.string.email_placeholder,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    ),
                    error = state.emailError
                )
                BazarTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password ?: "",
                    onValueChange = {
                        viewModel.onTriggerEvent(SignInEvent.PasswordChanged(it))
                    },
                    labelText = stringResource(R.string.password),
                    placeHolderResourceId = R.string.password_placeholder,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    iconResourceId = R.drawable.ic_ography_password_outline,
                    error = state.passwordError,
                    isSecure = true
                )
                BazarTextButton(
                    text = stringResource(R.string.forgot_password),
                    onClick = {}
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    BazarTheme.spacing.medium,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.login),
                    onClick = {
                        viewModel.onTriggerEvent(SignInEvent.SignInClicked)
                    },
                    enabled = viewModel.validateForm(),
                )

                SignInTextLink(onClick = {})
                OrWithHorizontalDivider()
                SignInButton(
                    text = stringResource(R.string.sign_in_with_google),
                    painter = painterResource(id = R.drawable.ic_google_original),
                    onClick = {}
                )
                SignInButton(
                    text = stringResource(R.string.sign_in_with_apple),
                    painter = painterResource(id = R.drawable.ic_apple_original),
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    SignInScreen(navController = rememberNavController())
}