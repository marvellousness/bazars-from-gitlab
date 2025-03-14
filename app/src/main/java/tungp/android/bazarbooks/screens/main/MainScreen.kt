package tungp.android.bazarbooks.screens.main

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.navigation.BottomNavigationBar
import tungp.android.bazarbooks.navigation.bottomNavigationItemsList
import tungp.android.bazarbooks.navigation.graphs.MainNavGraph
import tungp.android.bazarbooks.ui.theme.BazarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    rootNavController: NavHostController,
    homeNavController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }
    val topBarTitle by remember(currentRoute) {
        derivedStateOf {
            if (currentRoute != null) {
                bottomNavigationItemsList[bottomNavigationItemsList.indexOfFirst {
                    it.route == currentRoute
                }].title
            } else {
                bottomNavigationItemsList[0].title
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = BazarTheme.colors.surfaceContainerLowest,
                    scrolledContainerColor = BazarTheme.colors.primary,
                    actionIconContentColor = BazarTheme.colors.primary,
                    navigationIconContentColor = BazarTheme.colors.primary,
                    titleContentColor = BazarTheme.colors.primary,
                ),
                title = {
                    Text(text = topBarTitle)
                })
        },
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItemsList,
                currentRoute = currentRoute
            ) { currentNavigationItem ->
                homeNavController.navigate(currentNavigationItem.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    homeNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                        // Pop up to the start destination, clearing the back stack
                        popUpTo(startDestinationRoute) {
                            // Save the state of popped destinations
                            saveState = true
                        }
                    }

                    // Configure navigation to avoid multiple instances of the same destination
                    launchSingleTop = true

                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }
        }
    ) { innerPadding ->
        BazarSurface(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(paddingValues = innerPadding),
        ) {
            MainNavGraph(
                rootNavController = rootNavController,
                homeNavController = homeNavController
            )
        }
    }
}