package tungp.android.bazarbooks.screens.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.ui.theme.constants.MyColors
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun CartDetailScreen(
    rootNavController: NavController
) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Cyan)
//            .verticalScroll(rememberScrollState()),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Cart Details Screen",
//            fontSize = 40.sp,
//            color = Color.Black
//        )
//        Button(onClick = {
//            rootNavController.navigate(AuthRouteScreen.Login.route){
//                popUpTo(Graph.MainScreenGraph) {
//                    inclusive = true
//                }
//                launchSingleTop = true
//            }
//        }) {
//            Text("Go to Login")
//        }
//    }
    CartConfirmOrder()
}

@Composable
fun CartConfirmOrder() {
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
                    .padding(bottom = 20.dp,)
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(vertical = 21.dp,)
            ){
                Text("Address",
                    color = Color(0xFF121212),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 11.dp,start = 16.dp,)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 24.dp, start = 16.dp, end = 16.dp,)
                        .fillMaxWidth()
                ){
                    CoilImage(
                        imageModel = { R.drawable.ic_ography_location_location },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(end = 17.dp,)
                            .width(44.dp)
                            .height(44.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 5.dp, end = 4.dp,)
                            .weight(1f)
                    ){
                        Text("Utama Street No.20",
                            color = Color(0xFF121212),
                            fontSize = MyFontSize.body_large,
                            modifier = Modifier
                                .padding(bottom = 7.dp,)
                        )
                        Text("Dumbo Street No.20, Dumbo, New York 10001, United States",
                            color = Color(0xFFA5A5A5),
                            fontSize = MyFontSize.body_medium,
                        )
                    }
                    CoilImage(
                        imageModel = { R.drawable.ic_ography_chevron_right },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(top = 24.dp,)
                            .width(7.dp)
                            .height(14.dp)
                    )
                }
                OutlinedButton(
                    onClick = { println("Pressed!") },
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .padding(horizontal = 76.dp,)
                        .clip(shape = RoundedCornerShape(40.dp))
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF9F8FC),
                            shape = RoundedCornerShape(40.dp)
                        )
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(vertical = 12.dp,)
                    ){
                        Text("Change",
                            color = Color(0xFF54408C),
                            fontSize = MyFontSize.body_medium,
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(
                        color = MyColors.surfaceContainerLowest,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 21.dp,)
            ){
                Text("Summary",
                    color = Color(0xFF121212),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 20.dp,start = 17.dp,)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 13.dp, start = 17.dp, end = 17.dp,)
                        .fillMaxWidth()
                ){
                    Text("Price",
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_medium,
                    )
                    Text("$87.10",
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_medium,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 18.dp, start = 16.dp, end = 16.dp,)
                        .fillMaxWidth()
                ){
                    Text("Shipping",
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_medium,
                    )
                    Text("$2",
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_medium,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 18.dp, start = 16.dp, end = 16.dp,)
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFE8E8E8),
                        )
                ){
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 17.dp, start = 17.dp, end = 17.dp,)
                        .fillMaxWidth()
                ){
                    Text("Total Payment",
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_medium,
                    )
                    Text("$89.10",
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_medium,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 20.dp,)
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
                        .padding(horizontal = 17.dp,)
                        .fillMaxWidth()
                ){
                    Text("See details",
                        color = Color(0xFF54408C),
                        fontSize = MyFontSize.body_medium,
                        modifier = Modifier
                            .padding(end = 11.dp,)
                    )
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(4.dp)
                            .height(9.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(vertical = 21.dp, horizontal = 16.dp,)
            ){
                Text("Date and time",
                    color = Color(0xFF121212),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 17.dp,)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(end = 17.dp,)
                            .width(44.dp)
                            .height(44.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(end = 4.dp,)
                            .weight(1f)
                    ){
                        Text("Date & time",
                            color = Color(0xFF121212),
                            fontSize = MyFontSize.body_medium,
                            modifier = Modifier
                                .padding(bottom = 10.dp,)
                        )
                        Text("Choose date and time ",
                            color = Color(0xFF7A7A7A),
                            fontSize = MyFontSize.body_medium,
                        )
                    }
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(7.dp)
                            .height(14.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 34.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(vertical = 22.dp, horizontal = 16.dp,)
            ){
                Text("Payment",
                    color = Color(0xFF121212),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 16.dp,)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(end = 17.dp,)
                            .width(44.dp)
                            .height(44.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(end = 4.dp,)
                            .weight(1f)
                    ){
                        Text("Payment",
                            color = Color(0xFF121212),
                            fontSize = MyFontSize.body_medium,
                            modifier = Modifier
                                .padding(bottom = 9.dp,)
                        )
                        Text("Choose your payment",
                            color = Color(0xFF7A7A7A),
                            fontSize = MyFontSize.body_medium,
                        )
                    }
                    CoilImage(
                        imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(7.dp)
                            .height(14.dp)
                    )
                }
            }
            OutlinedButton(
                onClick = { println("Pressed!") },
                border = BorderStroke(0.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .padding(bottom = 39.dp, start = 24.dp, end = 24.dp,)
                    .clip(shape = RoundedCornerShape(48.dp))
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF54408C),
                        shape = RoundedCornerShape(48.dp)
                    )
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 17.dp,)
                ){
                    Text("Order",
                        color = MyColors.surfaceContainerLowest,
                        fontSize = MyFontSize.body_large,
                    )
                }
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

@Preview
@Composable
private fun CartDetailScreenPreview() {
    CartDetailScreen(rememberNavController())
}