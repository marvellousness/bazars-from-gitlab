package tungp.android.bazarbooks.screens.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.constants.MyColors
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun ProfileScreen() {
    ProfileContent()
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MyColors.surfaceContainerLowest)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader()
        ProfileList()
    }
}

@Composable
private fun ProfileHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        CoilImage(
            imageModel = { R.drawable.avatar },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(end = 15.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .size(56.dp)
        )
        Column(
            modifier = Modifier
                .padding(end = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = "John Doe",
                color = Color(0xFF121212),
                fontSize = MyFontSize.body_large,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "(+1) 234 567 890",
                color = Color(0xFFA5A5A5),
                fontSize = MyFontSize.body_medium,
            )
        }
        Text(
            text = "Logout",
            color = Color(0xFFEF5A56),
            fontSize = MyFontSize.body_medium,
            modifier = Modifier.clickable { /* TODO: Handle Logout */ }
        )
    }
}

@Composable
private fun ProfileList() {
    val menuItems = listOf(
        ProfileMenuItemData(R.drawable.ic_ography_profile_fill, "My Account"),
        ProfileMenuItemData(R.drawable.localtion, "Address"),
        ProfileMenuItemData(R.drawable.ic_ography_fire, "Offers & Promos"),
        ProfileMenuItemData(R.drawable.ic_ography_love_fill, "Your Favorites"),
        ProfileMenuItemData(R.drawable.ic_ography_menu_fill, "Order History"),
        ProfileMenuItemData(R.drawable.ic_ography_chat_fill, "Help Center")
    )

    Column {
        menuItems.forEach { item ->
            ProfileMenuItem(item)
        }
    }
}

private data class ProfileMenuItemData(
    val icon: Int,
    val title: String,
    val onClick: () -> Unit = {}
)

@Composable
private fun ProfileMenuItem(item: ProfileMenuItemData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        CoilImage(
            imageModel = { item.icon },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(end = 16.dp)
                .size(40.dp)
        )
        Text(
            text = item.title,
            color = GrayScale900,
            fontSize = MyFontSize.body_large,
            modifier = Modifier.weight(1f)
        )
        CoilImage(
            imageModel = { R.drawable.ic_ography_arrow_right },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}