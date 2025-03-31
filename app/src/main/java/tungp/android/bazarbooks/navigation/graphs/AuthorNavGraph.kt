package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.AuthorRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.author.AuthorsScreen

fun NavGraphBuilder.authorNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AuthorGraph,
        startDestination = AuthorRouteScreen.Authors.route
    ) {
        composable(route = AuthorRouteScreen.Authors.route) {
            AuthorsScreen(navController = navController)
        }
    }
} 