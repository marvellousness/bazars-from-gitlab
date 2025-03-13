package tungp.android.bazarbooks.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.OnboardingRouteScreen
import tungp.android.bazarbooks.screens.onboarding.OnboardingScreen

fun NavGraphBuilder.onboardingNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.OnboardingGraph,
        startDestination = OnboardingRouteScreen.Onboarding.route
    ) {
        composable(route = OnboardingRouteScreen.Onboarding.route) {
            OnboardingScreen(
                viewModel = hiltViewModel(),
                gotoAuth = {
                    rootNavController.navigate(Graph.AuthGraph) {
                        popUpTo(AuthRouteScreen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}