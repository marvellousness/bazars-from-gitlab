package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import kotlinx.serialization.json.Json
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.navigation.AuthorRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.author.AuthorDetailScreen
import tungp.android.bazarbooks.screens.author.AuthorsScreen

fun NavGraphBuilder.authorNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AuthorGraph,
        startDestination = AuthorRouteScreen.Authors.route
    ) {
        composable(route = AuthorRouteScreen.Authors.route) {
            AuthorsScreen(navController = navController)
        }
        composable(
            route = "authorDetail/{author}",
            arguments = listOf(
                navArgument("author") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("author")?.let { encodedJson ->
                val json = java.net.URLDecoder.decode(encodedJson, java.nio.charset.StandardCharsets.UTF_8.toString())
                val author = Json.decodeFromString<Author>(json)
                AuthorDetailScreen(
                    navController = navController,
                    author = author
                )
            }
        }
    }
} 