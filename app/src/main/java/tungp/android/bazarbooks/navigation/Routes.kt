package tungp.android.bazarbooks.navigation

object Graph {
    const val RootGraph = "rootGraph"
    const val OnboardingGraph = "onboardingGraph"
    const val AuthGraph = "authGraph"
    const val MainScreenGraph = "mainScreenGraph"
    const val CategoryGraph = "categoryGraph"
    const val CartGraph = "cartGraph"
    const val VendorGraph = "vendorGraph"
    const val AuthorGraph = "authorGraph"

    // Book Graph is used to navigate to the book detail screen    
    const val BookGraph = "bookGraph"
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
    object Cart : MainRouteScreen("cart")
}

sealed class CartRouteScreen(val route: String) {
    object CartDetail : CartRouteScreen("cartDetail")
}

sealed class CategoryRouteScreen(val route: String) {
    object CategoryDetail : CategoryRouteScreen("categoryDetail")
}

sealed class VendorRouteScreen(val route: String) {
    object BestVendors : VendorRouteScreen("bestVendors")
}

sealed class BookRouteScreen(val route: String) {
    object BookDetail : BookRouteScreen("bookDetail/{bookId}") {
        fun createRoute(bookId: String) = "bookDetail/$bookId"
    }
}

sealed class AuthorRouteScreen(val route: String) {
    object Authors : AuthorRouteScreen("authors")
}
