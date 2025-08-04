package tungp.android.bazarbooks.navigation

import kotlinx.serialization.json.Json
import tungp.android.bazarbooks.domain.model.User
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
    object Verification : AuthRouteScreen("verificationOtp/{userJson}") {
        fun createRoute(user: User): String {
            val userJson = Json.encodeToString(user)
            val encodedJson = URLEncoder.encode(userJson, StandardCharsets.UTF_8.toString())
            return "verificationOtp/$encodedJson"
        }

        fun getUserFromRoute(userJson: String): User {
            val decodedJson = URLDecoder.decode(userJson, StandardCharsets.UTF_8.toString())
            return Json.decodeFromString<User>(decodedJson)
        }
    }
}

sealed class MainRouteScreen(val route: String) {
    object Home : MainRouteScreen("home")
    object Profile : MainRouteScreen("profile")
    object Category : MainRouteScreen("category")
    object Cart : MainRouteScreen("cart")
}

sealed class CartRouteScreen(val route: String) {
    object CartDetail : CartRouteScreen("cartDetail")
    object LocationDetails : CartRouteScreen("locationChange")
    object OrderSuccess : CartRouteScreen("orderSuccess")
    object OrderRating : CartRouteScreen("orderRating")
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
