package tungp.android.bazarbooks.screens.auth.signup

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BackNavigationAction
import tungp.android.bazarbooks.components.BazarAppBar
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.components.BazarTextField
import tungp.android.bazarbooks.components.HeaderText
import tungp.android.bazarbooks.components.SubHeaderText
import tungp.android.bazarbooks.components.TextAnnotatedLinkClickable
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.domain.model.User
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.paddingDefault

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToOtpVerification: (user: User) -> Unit,
    onNavigationBack: () -> Unit,
) {
    val TAG = "~~~SignUpScreen"
    val scrollableState = rememberScrollState()
    val signUpState by viewModel.signUpState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = signUpState.isSignUpSuccess) {

        Log.d(TAG, "SignUpScreen: LaunchedEffect: ${signUpState.isSignUpSuccess}")

        if (signUpState.isSignUpSuccess && signUpState.user != null) {
            Log.d(TAG, "SignUpScreen: isSignUpSuccess && signUpState.user NOT NULL")
            onNavigateToOtpVerification(signUpState.user!!)
        }
    }

    BazarSurface {
        Scaffold(
            topBar = {
                BazarAppBar(
                    navigationIcon = { BackNavigationAction(onClick = onNavigationBack) })
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
                SignUpForm(
                    state = signUpState,
                    viewModel = viewModel,
                    onSignInClick = onNavigateToLogin
                )
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
        placeholderText = stringResource(R.string.username_placeholder),
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
        placeholderText = stringResource(R.string.email_placeholder),
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
        placeholderText = stringResource(R.string.password_placeholder),
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
            viewModel.onEvent(SignUpEvent.RegisterNewUser)
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