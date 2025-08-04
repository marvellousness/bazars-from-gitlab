package tungp.android.bazarbooks.screens.auth.otpverification

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tungp.android.bazarbooks.components.BackNavigationAction
import tungp.android.bazarbooks.components.BazarAppBar
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.components.HeaderText
import tungp.android.bazarbooks.components.SubHeaderText
import tungp.android.bazarbooks.components.button.BazarTextButton
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.domain.model.User
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.paddingDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    user: User,
    viewModel: VerificationViewModel = hiltViewModel(),
    onVerificationSuccess: () -> Unit,
    onBackClick: () -> Unit = {}
) {

    val TAG = "~~~VerificationScreen"

    Log.d(TAG, "user: $user")

    val state by viewModel.state.collectAsStateWithLifecycle()
    val content by viewModel.content.collectAsStateWithLifecycle()

    BazarSurface {
        Scaffold(
            topBar = {
                BazarAppBar(
                    navigationIcon = { BackNavigationAction(onClick = onBackClick) }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .paddingDefault()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else if (state.error != null) {
                    Text(text = state.error!!)
                } else if (content != null) {
                    HeaderText(text = content!!.title)
                    SubHeaderText(text = "${content!!.subtitle} to ${state.emailOrPhone}")
                    Spacer(modifier = Modifier.padding(BazarTheme.spacing.medium))

                    VerificationCodeInput(
                        code = state.code,
                        onCodeChange = { viewModel.onEvent(VerificationEvent.CodeChanged(it)) }
                    )
                    Spacer(modifier = Modifier.padding(BazarTheme.spacing.medium))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = content!!.verify_button,
                        onClick = { viewModel.onEvent(VerificationEvent.VerifyCode) },
                        enabled = state.code.all { it != null },
                    )

                    BazarTextButton(
                        text = content!!.resend_button,
                        onClick = { viewModel.onEvent(VerificationEvent.ResendCode) }
                    )
                }
            }
        }
    }
}