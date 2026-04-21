package tungp.android.bazarbooks.screens.main.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.extension.TextFieldView
import tungp.android.bazarbooks.ui.theme.constants.MyColors
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountScreen(
    navController: NavController,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        MyAccountContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun MyAccountContent(modifier: Modifier) {
    val textField1 = remember { mutableStateOf("") }
    val textField2 = remember { mutableStateOf("") }
    val textField3 = remember { mutableStateOf("") }
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
            Box(
                modifier = Modifier
                    .padding(bottom = 71.dp,)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFF9F8FC),
                            )
                    ){
                        CoilImage(
                            imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .offset(x = -137.dp, y = 50.dp)
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 50.dp)
                        .width(100.dp)
                        .height(100.dp)
                )
            }
            Text("Change Picture",
                color = Color(0xFF54408C),
                fontSize = MyFontSize.body_large,
                modifier = Modifier
                    .padding(bottom = 48.dp,start = 133.dp,end = 133.dp,)
            )
            Text("Name",
                color = Color(0xFF121212),
                fontSize = MyFontSize.body_medium,
                modifier = Modifier
                    .padding(bottom = 7.dp,start = 26.dp,)
            )
            TextFieldView(
                placeholder = "John",
                value = textField1.value,
                onValueChange = { newText -> textField1.value = newText },
                textStyle = TextStyle(
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(17.dp)
            )
            Text("Email",
                color = Color(0xFF121212),
                fontSize = MyFontSize.body_medium,
                modifier = Modifier
                    .padding(bottom = 8.dp,start = 26.dp,)
            )
            TextFieldView(
                placeholder = "Johndoe@email.com",
                value = textField2.value,
                onValueChange = { newText -> textField2.value = newText },
                textStyle = TextStyle(
                    color = Color(0xFF121212),
                    fontSize = MyFontSize.body_large,
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(17.dp)
            )
            Text("Phone Number",
                color = Color(0xFF121212),
                fontSize = MyFontSize.body_medium,
                modifier = Modifier
                    .padding(bottom = 8.dp,start = 26.dp,)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 15.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 20.dp,)
                        .width(19.dp)
                        .height(19.dp)
                )
                TextFieldView(
                    placeholder = "(+1) 234 567 890",
                    value = textField3.value,
                    onValueChange = { newText -> textField3.value = newText },
                    textStyle = TextStyle(
                        color = Color(0xFF121212),
                        fontSize = MyFontSize.body_large,
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 14.dp,)
                )
            }
            Text("Password",
                color = Color(0xFF121212),
                fontSize = MyFontSize.body_medium,
                modifier = Modifier
                    .padding(bottom = 8.dp,start = 26.dp,)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 40.dp, start = 24.dp, end = 24.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE8E8E8),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .height(24.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF9F9F9),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 16.dp,)
            ){
                CoilImage(
                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(78.dp)
                        .height(8.dp)
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
            OutlinedButton(
                onClick = { println("Pressed!") },
                border = BorderStroke(0.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .padding(bottom = 63.dp, start = 24.dp, end = 24.dp,)
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
                    Text("Save Changes",
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
private fun MyAccountScreenPreview() {
    MyAccountContent(modifier = Modifier)
}