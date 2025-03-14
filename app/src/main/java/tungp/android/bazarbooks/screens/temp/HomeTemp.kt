package tungp.android.bazarbooks.screens.temp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.ui.theme.PreviewTheme
import tungp.android.bazarbooks.ui.theme.constants.MyColors
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun HomeTemp() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = MyColors.surfaceContainerLowest,
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = MyColors.surfaceContainerLowest,
                )
                .verticalScroll(rememberScrollState())
        ) {
            CoilImage(
                imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 24.dp, end = 24.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F8FC),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(top = 1.dp, bottom = 1.dp, start = 23.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text(
                        "Special Offer",
                        color = Color(0xFF121212),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                    )
                    Text(
                        "Discount 25%",
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_medium,
                        modifier = Modifier
                            .padding(bottom = 16.dp, start = 2.dp, end = 2.dp)
                    )
                    OutlinedButton(
                        onClick = { println("Pressed!") },
                        border = BorderStroke(0.dp, Color.Transparent),
                        colors = ButtonDefaults.outlinedButtonColors(),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(40.dp))
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF54408C),
                                shape = RoundedCornerShape(40.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        ) {
                            Text(
                                "Order Now",
                                color = MyColors.surfaceContainerLowest,
                                fontSize = MyFontSize.body_medium,
                            )
                        }
                    }
                }
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(3.dp))
                        .width(99.dp)
                        .height(145.dp)
                )
            }
            CoilImage(
                imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(bottom = 32.dp, start = 175.dp, end = 175.dp)
                    .height(8.dp)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 17.dp, start = 23.dp, end = 23.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Top of Week",
                    color = Color(0xFF121212),
                    fontSize = 18.sp,
                )
                Text(
                    "See all",
                    color = Color(0xFF54408C),
                    fontSize = MyFontSize.body_medium,
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .width(127.dp)
                        .height(150.dp)
                )
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .width(127.dp)
                        .height(150.dp)
                )
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .width(127.dp)
                        .height(150.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "The Kite Runner",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
                Text(
                    "The Kite Runner",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
                Text(
                    "The Kite Runner",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_medium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 39.dp, start = 25.dp, end = 25.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "$14.99",
                    color = Color(0xFF54408C),
                    fontSize = MyFontSize.body_small,
                )
                Text(
                    "$20.99",
                    color = Color(0xFF54408C),
                    fontSize = MyFontSize.body_small,
                )
                Text(
                    "$14.99",
                    color = Color(0xFF54408C),
                    fontSize = MyFontSize.body_small,
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 17.dp, start = 25.dp, end = 25.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Best Vendors",
                    color = Color(0xFF121212),
                    fontSize = 18.sp,
                )
                Text(
                    "See all",
                    color = Color(0xFF54408C),
                    fontSize = MyFontSize.body_medium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 37.dp, start = 7.dp, end = 7.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .width(80.dp)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(top = 31.dp)
                            .height(17.dp)
                            .fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .width(80.dp)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 10.dp)
                ) {
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .height(27.dp)
                            .fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .width(80.dp)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 5.dp)
                ) {
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(top = 29.dp)
                            .height(21.dp)
                            .fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .width(80.dp)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 5.dp)
                ) {
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .height(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 35.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Authors",
                    color = Color(0xFF121212),
                    fontSize = 18.sp,
                )
                Text(
                    "See all",
                    color = Color(0xFF54408C),
                    fontSize = MyFontSize.body_medium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .width(102.dp)
                        .height(102.dp)
                )
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .width(102.dp)
                        .height(102.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 11.dp)
                    .height(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "John Freeman",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                    modifier = Modifier
                        .padding(end = 34.dp)
                )
                Text(
                    "Tess Gunty",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                }
                Text(
                    "Richard Perston",
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 51.dp, start = 25.dp, end = 25.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Writer",
                    color = Color(0xFFA5A5A5),
                    fontSize = MyFontSize.body_medium,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .weight(1f)
                )
                Text(
                    "Novelist",
                    color = Color(0xFFA5A5A5),
                    fontSize = MyFontSize.body_medium,
                    modifier = Modifier
                        .padding(end = 86.dp)
                )
                Text(
                    "Writer",
                    color = Color(0xFFA5A5A5),
                    fontSize = MyFontSize.body_medium,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9),
                    )
                    .padding(vertical = 12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 49.dp, end = 49.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .width(24.dp)
                            .padding(horizontal = 3.dp)
                    ) {
                        CoilImage(
                            imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .height(21.dp)
                                .fillMaxWidth()
                        )
                    }
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(18.dp)
                            .height(20.dp)
                    )
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(16.dp)
                            .height(20.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 13.dp, start = 44.dp, end = 44.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Home",
                        color = Color(0xFF54408C),
                        fontSize = MyFontSize.body_small,
                    )
                    Text(
                        "Category",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                    )
                    Text(
                        "Cart",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                    )
                    Text(
                        "Profile",
                        color = Color(0xFFA5A5A5),
                        fontSize = MyFontSize.body_small,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 120.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .height(5.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF121212),
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeTempPreview() {
    PreviewTheme {
        HomeTemp()
    }
}