package tungp.android.bazarbooks.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.MainRouteScreen
import tungp.android.bazarbooks.screens.main.HomeScreen
import tungp.android.bazarbooks.screens.category.CategoryScreen
import tungp.android.bazarbooks.screens.main.ProfileScreen
import tungp.android.bazarbooks.screens.main.cart.CartScreen

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
            HomeScreen(navController = rootNavController)
        }
        composable(route = MainRouteScreen.Category.route) {
            CategoryScreen(navController = rootNavController)
        }
        composable(route = MainRouteScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = MainRouteScreen.Cart.route) {
            CartScreen(navController = rootNavController)
        }
    }
}