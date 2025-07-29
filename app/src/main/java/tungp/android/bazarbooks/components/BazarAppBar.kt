package tungp.android.bazarbooks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.ui.theme.BazarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BazarAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
) {
    val colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = BazarTheme.colors.surfaceContainerLowest,
        scrolledContainerColor = BazarTheme.colors.primary,
        titleContentColor = BazarTheme.colors.primary,
        navigationIconContentColor = BazarTheme.colors.primary,
        actionIconContentColor = BazarTheme.colors.primary
    )
    CenterAlignedTopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Bazar App Bar")
@Composable
fun BazarAppBarPreview() {

    val actions: @Composable RowScope.() -> Unit = {
        IconButton(onClick = { }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BazarAppBar(
            title = {
                HeaderText(text = "Bazar.books")
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            actions = actions
        )
        BazarAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = { NotificationAction(onClick = { }) }
        )

    }
}
