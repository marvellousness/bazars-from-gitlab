//package tungp.android.bazarbooks.screens.auth.signup
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import tungp.android.bazarbooks.navigation.AuthRouteScreen
//import tungp.android.bazarbooks.navigation.Graph
//import tungp.android.bazarbooks.ui.theme.BazarTheme
//
//@Composable
//fun SignUpScreen(navController: NavController) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Register Screen")
//        Spacer(modifier = Modifier.height(30.dp))
//        Button(onClick = {
//            navController.navigate(Graph.MainScreenGraph) {
//                popUpTo(AuthRouteScreen.SignUp.route) {
//                    inclusive = true
//                }
//            }
//        }) {
//            Text("Register")
//        }
//        Spacer(modifier = Modifier.height(30.dp))
//        Button(onClick = {
//            navController.navigateUp()
//        }) {
//            Text("Already have a account? Login")
//        }
//    }
//}
//
//@Preview
//@Composable
//private fun SignUpScreenPreview() {
//    BazarTheme {
//        SignUpScreen(navController = rememberNavController())
//    }
//}