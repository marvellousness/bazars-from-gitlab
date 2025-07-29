package tungp.android.bazarbooks.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationAction(count: Int = 0, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        if (count == 0) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications"
            )
        } else {
            BadgedBox(
                modifier = Modifier.fillMaxWidth(),
                badge = {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        val badgeContent = if (count > 99) "99+" else count.toString()
                        Text(text = badgeContent)
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }
        }
    }
}

@Composable
fun BackNavigationAction(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
    }
}