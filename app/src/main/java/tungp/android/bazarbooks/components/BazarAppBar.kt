package tungp.android.bazarbooks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.ThemedPreview

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
    ThemedPreview {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BazarAppBar(
                title = {
                    HeaderText(text = "Bazar.books")
                },
                navigationIcon = {
                    BackNavigationAction(onClick = {})
                },
                actions = {
                    NotificationAction(
                        icon = Icons.Default.Notifications,
                        badgeNumber = 10,
                        onClick = {}
                    )
                }
            )
            BazarAppBar(
                navigationIcon = {
                    BackNavigationAction(icon = Icons.Default.Search, onClick = {})
                },
                actions = { NotificationAction(onClick = { }) }
            )
        }
    }
}

@Composable
fun AppBarTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = BazarTheme.typography.titleLarge,
        color = GrayScale900,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationAction(
    icon: ImageVector = Icons.Default.Notifications,
    badgeNumber: Int = 0,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        if (badgeNumber == 0) {
            Icon(imageVector = icon, contentDescription = "Notifications")
        } else {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        val badgeNumberText = if (badgeNumber > 10) "9+" else "$badgeNumber"
                        Text(text = badgeNumberText)
                    }
                }) {
                Icon(imageVector = icon, contentDescription = "Notifications")
            }
        }
    }
}

@Composable
fun BackNavigationAction(
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(icon, contentDescription = "Back")
    }
}