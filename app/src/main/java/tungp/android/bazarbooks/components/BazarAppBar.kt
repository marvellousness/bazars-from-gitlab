package tungp.android.bazarbooks.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.PreviewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BazarAppBar(
    pageTitle: String,
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = pageTitle)
        },
        navigationIcon = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                BadgedBox(badge = {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text("3")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BazarTheme.colors.surfaceContainerLowest,
            scrolledContainerColor = BazarTheme.colors.primary,
            titleContentColor = BazarTheme.colors.primary,
            navigationIconContentColor = BazarTheme.colors.primary,
            actionIconContentColor = BazarTheme.colors.primary
        )
    )
}


@Preview(showBackground = true)
@Composable
fun CustomAppBarPreview() {
    PreviewTheme {
        BazarAppBar(
            pageTitle = "Custom App Bar Title",
            onSearchClick = { /* Handle search click */ },
            onNotificationClick = { /* Handle notification click */ }
        )
    }
}