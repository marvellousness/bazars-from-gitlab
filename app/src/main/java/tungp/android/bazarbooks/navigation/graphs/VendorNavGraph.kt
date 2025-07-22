package tungp.android.bazarbooks.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.VendorRouteScreen
import tungp.android.bazarbooks.screens.vendor.VendorsScreen

fun NavGraphBuilder.vendorNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.VendorGraph,
        startDestination = VendorRouteScreen.BestVendors.route
    ) {
        composable(route = VendorRouteScreen.BestVendors.route) {
            VendorsScreen(navController = navController)
        }
    }
} 