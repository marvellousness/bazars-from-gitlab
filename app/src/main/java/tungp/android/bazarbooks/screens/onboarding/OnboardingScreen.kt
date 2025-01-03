package tungp.android.bazarbooks.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.Graph

@Composable
fun OnboardingScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Onboarding Screen")
        Button(onClick = {
            navController.navigate(Graph.AuthGraph) {
                popUpTo(AuthRouteScreen.Login.route) {
                    inclusive = true
                }
            }
        }) {
            Text(text = "Go to login")
        }
    }
}