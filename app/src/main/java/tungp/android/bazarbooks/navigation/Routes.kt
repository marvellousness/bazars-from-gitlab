package tungp.android.bazarbooks.navigation

object Graph {
    const val RootGraph = "rootGraph"
    const val OnboardingGraph = "onboardingGraph"
    const val AuthGraph = "authGraph"
    const val MainScreenGraph = "mainScreenGraph"
    const val CategoryGraph = "categoryGraph"
    const val CardGraph = "cardGraph"
}

sealed class OnboardingRouteScreen(val route: String) {
    object Onboarding : OnboardingRouteScreen("onboarding")
}

sealed class AuthRouteScreen(val route: String) {
    object Login : AuthRouteScreen("login")
    object SignUp : AuthRouteScreen("signUp")
    object Forget : AuthRouteScreen("forget")
}

sealed class MainRouteScreen(val route: String) {
    object Home : MainRouteScreen("home")
    object Profile : MainRouteScreen("profile")
    object Category : MainRouteScreen("category")
    object Card : MainRouteScreen("card")
}

sealed class CardRouteScreen(val route: String) {
    object CardDetail : CardRouteScreen("cardDetail")
}

sealed class CategoryRouteScreen(val route: String) {
    object CategoryDetail : CategoryRouteScreen("categoryDetail")
}