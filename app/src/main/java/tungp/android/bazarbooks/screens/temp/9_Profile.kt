package tungp.android.bazarbooks.screens.temp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import tungp.android.bazarbooks.ui.theme.constants.MyColors
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = MyColors.surfaceContainerLowest,
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = MyColors.surfaceContainerLowest,
                )
                .verticalScroll(rememberScrollState())
        ){
            CoilImage(
                imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(bottom = 24.dp,)
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                    )
                    .padding(bottom = 16.dp,)
            ){
                Column(
                    modifier = Modifier
                        .padding(bottom = 15.dp,)
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFE8E8E8),
                        )
                ){
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 24.dp,)
                        .fillMaxWidth()
                ){
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(end = 15.dp,)
                            .clip(shape = RoundedCornerShape(100.dp))
                            .width(56.dp)
                            .height(56.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(end = 4.dp,)
                            .weight(1f)
                    ){
                        Text("John Doe",
                            color = Color(0xFF121212),
                            fontSize = MyFontSize.body_large,
                            modifier = Modifier
                                .padding(bottom = 8.dp,)
                        )
                        Text("(+1) 234 567 890",
                            color = Color(0xFFA5A5A5),
                            fontSize = MyFontSize.body_medium,
                        )
                    }
                    Text("Logout",
                        color = Color(0xFFEF5A56),
                        fontSize = MyFontSize.body_medium,
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                    )
                    .padding(vertical = 16.dp, horizontal = 24.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 18.dp,)
                        .width(40.dp)
                        .height(40.dp)
                )
                Text("My Account",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ){
                }
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                    )
                    .padding(vertical = 16.dp, horizontal = 24.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 17.dp,)
                        .width(40.dp)
                        .height(40.dp)
                )
                Text("Address",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ){
                }
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                    )
                    .padding(vertical = 16.dp, horizontal = 24.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 17.dp,)
                        .width(40.dp)
                        .height(40.dp)
                )
                Text("Offers & Promos",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ){
                }
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                    )
                    .padding(vertical = 16.dp, horizontal = 24.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 17.dp,)
                        .width(40.dp)
                        .height(40.dp)
                )
                Text("Your Favorites",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ){
                }
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                    )
                    .padding(vertical = 16.dp, horizontal = 24.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 17.dp,)
                        .width(40.dp)
                        .height(40.dp)
                )
                Text("Order History",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ){
                }
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 85.dp,)
                    .height(24.dp)
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                    )
                    .padding(vertical = 16.dp, horizontal = 24.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 18.dp,)
                        .width(40.dp)
                        .height(40.dp)
                )
                Text("Help Center",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ){
                }
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9),
                    )
                    .padding(vertical = 16.dp,)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 4.dp, start = 48.dp, end = 48.dp,)
                        .fillMaxWidth()
                ){
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(19.dp)
                            .height(20.dp)
                    )
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(18.dp)
                            .height(20.dp)
                    )
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    Column(
                        modifier = Modifier
                            .width(24.dp)
                            .padding(horizontal = 4.dp,)
                    ){
                        CoilImage(
                            imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .padding(top = 2.dp,)
                                .height(20.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 13.dp, start = 44.dp, end = 44.dp,)
                        .fillMaxWidth()
                ){
                    Text("Home",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                    )
                    Text("Category",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                    )
                    Text("Cart (3)",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                    )
                    Text("Profile",
                        color = Color(0xFF54408C),
                        fontSize = MyFontSize.body_small,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 120.dp,)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .height(5.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF121212),
                            shape = RoundedCornerShape(20.dp)
                        )
                ){
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfilePreview() {
    Profile()
}