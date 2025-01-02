package tungp.android.bazarbooks.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.MainRouteScreen
import tungp.android.bazarbooks.screens.main.HomeScreen
import tungp.android.bazarbooks.screens.main.CategoryScreen
import tungp.android.bazarbooks.screens.main.ProfileScreen
import tungp.android.bazarbooks.screens.main.CardScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
) {
    NavHost(
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.Home.route
    ) {
        composable(route = MainRouteScreen.Home.route) {
            HomeScreen()
        }
        composable(route = MainRouteScreen.Category.route) {
            CategoryScreen(navController = rootNavController)
        }
        composable(route = MainRouteScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = MainRouteScreen.Card.route) {
            CardScreen(navController = rootNavController)
        }
    }
}