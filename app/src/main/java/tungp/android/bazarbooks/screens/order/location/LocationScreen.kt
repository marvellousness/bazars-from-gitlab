package tungp.android.bazarbooks.screens.order.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tungp.android.bazarbooks.components.BackNavigationAction
import tungp.android.bazarbooks.components.BazarAppBar
import tungp.android.bazarbooks.components.HeaderText
import tungp.android.bazarbooks.components.NotificationAction
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun LocationScreen(
    navController: NavController,
    viewModel: LocationViewModel = hiltViewModel(),
) {
    LocationContainer(
        viewModel,
        onNavigationClicked = {
            navController.popBackStack()
        },
        onActionClicked = { })
}

@Composable
fun LocationContainer(
    viewModel: LocationViewModel,
    onNavigationClicked: () -> Unit,
    onActionClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            BazarAppBar(
                title = { HeaderText(text = "Location") },
                navigationIcon = { BackNavigationAction(onClick = onNavigationClicked) },
                actions = { NotificationAction(onClick = onActionClicked) }
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = BazarTheme.colors.background)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}