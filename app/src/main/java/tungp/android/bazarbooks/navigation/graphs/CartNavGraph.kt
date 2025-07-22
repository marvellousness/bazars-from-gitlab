package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.CartRouteScreen
import tungp.android.bazarbooks.screens.order.OrderScreen
import tungp.android.bazarbooks.screens.order.location.LocationScreen
import tungp.android.bazarbooks.screens.order.status.OrderRatingScreen
import tungp.android.bazarbooks.screens.order.status.OrderSuccessScreen

fun NavGraphBuilder.cartNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.CartGraph,
        startDestination = CartRouteScreen.CartDetail.route
    ) {
        composable(route = CartRouteScreen.CartDetail.route) {
            OrderScreen(rootNavController = rootNavController)
        }
        composable(route = CartRouteScreen.LocationDetails.route) {
            LocationScreen(navController = rootNavController)
        }
        composable(route = CartRouteScreen.OrderSuccess.route) {
            OrderSuccessScreen(navController = rootNavController)
        }
        composable(route = CartRouteScreen.OrderRating.route) {
            OrderRatingScreen(navController = rootNavController)
        }
    }
}