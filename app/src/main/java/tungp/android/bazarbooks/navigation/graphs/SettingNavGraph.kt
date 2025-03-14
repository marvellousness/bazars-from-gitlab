package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.CartRouteScreen
import tungp.android.bazarbooks.screens.cart.CartDetailScreen

fun NavGraphBuilder.cartNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.CartGraph,
        startDestination = CartRouteScreen.CartDetail.route
    ) {
        composable(route = CartRouteScreen.CartDetail.route) {
            CartDetailScreen(rootNavController = rootNavController)
        }
    }
}