package tungp.android.bazarbooks.screens.cart

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
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.Graph

@Composable
fun CartDetailScreen(
    rootNavController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cart Details Screen",
            fontSize = 40.sp,
            color = Color.Black
        )
        Button(onClick = {
            rootNavController.navigate(AuthRouteScreen.Login.route){
                popUpTo(Graph.MainScreenGraph) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }) {
            Text("Go to Login")
        }
    }
}

@Preview
@Composable
private fun CartDetailScreenPreview() {
    CartDetailScreen(rememberNavController())
}