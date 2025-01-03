package tungp.android.bazarbooks.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tungp.android.bazarbooks.navigation.CategoryRouteScreen

@Composable
fun CategoryScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Category Screen",
            fontSize = 40.sp,
            color = Color.Black

        )
        Button(onClick = {
            navController.navigate(CategoryRouteScreen.CategoryDetail.route)
        }) {
            Text("Go to Category Detail Page")
        }
    }
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    CategoryScreen(
        navController = rememberNavController()
    )
}