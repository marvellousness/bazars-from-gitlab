package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.auth.ForgetPasswordScreen
import tungp.android.bazarbooks.screens.auth.LoginScreen
import tungp.android.bazarbooks.screens.auth.SignUpScreen

fun NavGraphBuilder.authNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.AuthGraph, startDestination = AuthRouteScreen.Login.route
    ) {
        composable(route = AuthRouteScreen.Login.route) {
            LoginScreen(navController = rootNavController)
        }
        composable(route = AuthRouteScreen.SignUp.route) {
            SignUpScreen(navController = rootNavController)
        }
        composable(route = AuthRouteScreen.Forget.route) {
            ForgetPasswordScreen(navController = rootNavController)
        }
    }
}