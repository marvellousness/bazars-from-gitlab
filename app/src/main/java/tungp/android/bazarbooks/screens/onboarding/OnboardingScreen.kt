package tungp.android.bazarbooks.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun OnboardingScreen(navController: NavController) {
    BazarSurface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Onboarding Screen")
            PrimaryButton(
                text = "Go to login",
                onClick = {
                    navController.navigate(Graph.AuthGraph) {
                        popUpTo(AuthRouteScreen.Login.route) {
                            inclusive = true
                        }
                    }
                })
        }
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    BazarTheme {
        OnboardingScreen(rememberNavController())
    }
}