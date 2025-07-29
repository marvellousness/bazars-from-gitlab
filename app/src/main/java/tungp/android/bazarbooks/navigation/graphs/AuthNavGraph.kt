package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.auth.forgotpassword.ForgetPasswordScreen
import tungp.android.bazarbooks.screens.auth.signin.SignInScreen
import tungp.android.bazarbooks.screens.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.AuthGraph, startDestination = AuthRouteScreen.Login.route
    ) {
        composable(route = AuthRouteScreen.Login.route) {
            SignInScreen(
                onSignInSuccess = {
                    rootNavController.navigate(Graph.MainScreenGraph) {
                        popUpTo(Graph.AuthGraph) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onSignUpClick = {},
                onForgotPasswordClick = {},
                onBackClick = {},
            )
        }
        composable(route = AuthRouteScreen.SignUp.route) {
            SignUpScreen(navController = rootNavController)
        }
        composable(route = AuthRouteScreen.Forget.route) {
            ForgetPasswordScreen(navController = rootNavController)
        }
    }
}