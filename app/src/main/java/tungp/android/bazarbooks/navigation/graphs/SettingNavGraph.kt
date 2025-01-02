package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.CardRouteScreen
import tungp.android.bazarbooks.screens.card.CardDetailScreen

fun NavGraphBuilder.cardNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.CardGraph,
        startDestination = CardRouteScreen.CardDetail.route
    ) {
        composable(route = CardRouteScreen.CardDetail.route) {
            CardDetailScreen(rootNavController = rootNavController)
        }
    }
}