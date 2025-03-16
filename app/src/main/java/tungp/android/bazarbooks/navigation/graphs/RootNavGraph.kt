package tungp.android.bazarbooks.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.main.MainScreen

@Composable
fun RootNavGraph(isAuth: Boolean) {
    val rootNavController: NavHostController = rememberNavController()

    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = if (isAuth) Graph.MainScreenGraph else Graph.OnboardingGraph
    ) {
        onboardingNavGraph(rootNavController)
        authNavGraph(rootNavController = rootNavController)
        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController = rootNavController)
        }
        categoryNavGraph(rootNavController)
        cartNavGraph(rootNavController)
        vendorNavGraph(rootNavController)
    }
}