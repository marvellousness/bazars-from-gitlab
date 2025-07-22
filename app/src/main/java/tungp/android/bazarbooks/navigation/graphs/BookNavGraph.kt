package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.BookRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.bookdetail.BookDetailScreen

fun NavGraphBuilder.bookNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.BookGraph,
        startDestination = BookRouteScreen.BookDetail.route
    ) {
        composable(
            route = BookRouteScreen.BookDetail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookDetailScreen(
                bookId = bookId,
                navController = navController
            )
        }
    }
}