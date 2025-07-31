package tungp.android.bazarbooks.screens.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BackNavigationAction
import tungp.android.bazarbooks.components.BazarAppBar
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.components.BazarTextField
import tungp.android.bazarbooks.components.HeaderText
import tungp.android.bazarbooks.components.SubHeaderText
import tungp.android.bazarbooks.components.TextAnnotatedLinkClickable
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.paddingDefault

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignInClick: () -> Unit = {},
    onSignInSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val scrollableState = rememberScrollState()
    val state by viewModel.state.collectAsState()

    // TODO: Navigate to Home screen when isSignUpSuccess
    LaunchedEffect(key1 = state.isSignUpSuccess) {
        if (state.isSignUpSuccess) {
            onSignInSuccess()
        }
    }

    BazarSurface {
        Scaffold(
            topBar = {
                BazarAppBar(
                    navigationIcon = { BackNavigationAction(onClick = onBackClick) })
            }
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(BazarTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .paddingDefault()
                    .verticalScroll(state = scrollableState)
            ) {
                HeaderText(text = stringResource(R.string.sign_up))
                SubHeaderText(stringResource(R.string.sign_up_subtitle))
                Spacer(modifier = Modifier.height(BazarTheme.spacing.small))
                SignUpForm(state, viewModel, onSignInClick)
            }
        }
    }
}

@Composable
private fun ColumnScope.SignUpForm(
    state: SignUpState,
    viewModel: SignUpViewModel,
    onSignInClick: () -> Unit,
) {
    BazarTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.username ?: "",
        onValueChange = {
            viewModel.onEvent(SignUpEvent.UsernameChanged(it))
        },
        labelText = stringResource(R.string.username),
        placeHolderResourceId = R.string.username_placeholder,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Default
        ),
        error = state.usernameError
    )
    BazarTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.email ?: "",
        onValueChange = {
            viewModel.onEvent(SignUpEvent.EmailChanged(it))
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
            viewModel.onEvent(SignUpEvent.PasswordChanged(it))
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
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.register),
        onClick = {
            viewModel.onEvent(SignUpEvent.Register)
        },
        enabled = !state.isLoading,
    )
    TextAnnotatedLinkClickable(
        text1 = stringResource(id = R.string.have_an_account),
        text2 = stringResource(id = R.string.sign_in),
        onClick = onSignInClick
    )
    Spacer(modifier = Modifier.weight(1f))
    TextAnnotatedLinkClickable(
        text1 = stringResource(id = R.string.term_of_service_first),
        text2 = stringResource(id = R.string.term_of_service_second),
        centerAligned = true,
        onClick = {}
    )
}