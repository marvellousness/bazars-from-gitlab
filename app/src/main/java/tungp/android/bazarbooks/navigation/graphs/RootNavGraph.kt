package tungp.android.bazarbooks.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.bookdetail.BookDetailScreen
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

        composable(
            "bookDetail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "android-app://androidx.navigation/bookDetail/{bookId}"
            })
        ) { entry ->
            val bookId = entry.arguments?.getString("bookId")
            bookId?.let { id ->
                BookDetailScreen(bookId = id, navController = rootNavController)
            }
        }
    }
}