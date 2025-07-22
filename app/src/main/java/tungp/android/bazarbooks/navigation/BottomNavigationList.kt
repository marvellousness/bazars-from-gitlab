package tungp.android.bazarbooks.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart

val bottomNavigationItemsList = listOf(
    NavigationItem(
        title = "Home",
        route = MainRouteScreen.Home.route,
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
    ),
    NavigationItem(
        title = "Category",
        route = MainRouteScreen.Category.route,
        selectedIcon = Icons.Filled.Folder,
        unSelectedIcon = Icons.Outlined.Folder
    ),
    NavigationItem(
        title = "Cart",
        route = MainRouteScreen.Cart.route,
        selectedIcon = Icons.Filled.ShoppingCart,
        unSelectedIcon = Icons.Outlined.ShoppingCart,
        hasBadgeDot = true
    ),
    NavigationItem(
        title = "Profile",
        route = MainRouteScreen.Profile.route,
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person,
    ),
)