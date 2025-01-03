package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.CategoryRouteScreen
import tungp.android.bazarbooks.screens.category.CategoryDetailScreen

fun NavGraphBuilder.categoryNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.CategoryGraph,
        startDestination = CategoryRouteScreen.CategoryDetail.route
    ) {
        composable(route = CategoryRouteScreen.CategoryDetail.route) {
            CategoryDetailScreen(navController = rootNavController)
        }
    }
}