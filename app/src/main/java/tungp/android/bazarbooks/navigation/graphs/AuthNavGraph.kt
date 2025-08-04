package tungp.android.bazarbooks.navigation.graphs

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.auth.forgotpassword.ForgetPasswordScreen
import tungp.android.bazarbooks.screens.auth.otpverification.VerificationScreen
import tungp.android.bazarbooks.screens.auth.signin.SignInScreen
import tungp.android.bazarbooks.screens.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AuthGraph, startDestination = AuthRouteScreen.Login.route
    ) {
        val TAG = "~~~authNavGraph"

        composable(route = AuthRouteScreen.Login.route) {
            SignInScreen(
                onSignInSuccess = {
                    navController.navigate(Graph.MainScreenGraph) {
                        popUpTo(Graph.AuthGraph) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onSignUpClick = {
                    navController.navigate(AuthRouteScreen.SignUp.route) {
                        launchSingleTop = true
                    }
                },
                onForgotPasswordClick = { navController.navigate(AuthRouteScreen.Forget.route) },
                onBackClick = { navController.navigateUp() },
            )
        }
        composable(route = AuthRouteScreen.SignUp.route) {
            SignUpScreen(
                viewModel = hiltViewModel(),
                onNavigateToOtpVerification = { user ->
                    Log.d(TAG, "onNavigateToVerification: user=$user")
                    navController.navigate(AuthRouteScreen.Verification.createRoute(user = user))
                },
                onNavigateToLogin = {
                    navController.navigate(AuthRouteScreen.Login.route) {
                        popUpTo(AuthRouteScreen.SignUp.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = AuthRouteScreen.Forget.route) {
            ForgetPasswordScreen(navController = navController)
        }
        composable(route = AuthRouteScreen.Verification.route) { backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("userJson")
            if (userJson != null) {
                val user = AuthRouteScreen.Verification.getUserFromRoute(userJson)

                Log.d(TAG, "authNavGraph: ")
                VerificationScreen(
                    viewModel = hiltViewModel(),
                    user = user,
                    onVerificationSuccess = {},
                    onBackClick = { }
                )
            }
        }
    }
}