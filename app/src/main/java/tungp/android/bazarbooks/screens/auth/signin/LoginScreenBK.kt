//package tungp.android.bazarbooks.screens.auth.login
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
//
//@Composable
//fun LoginScreen(navController: NavController) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Login Screen")
//        Spacer(modifier = Modifier.height(30.dp))
//        Button(onClick = {
//            navController.navigate(Graph.MainScreenGraph) {
//                popUpTo(AuthRouteScreen.Login.route) {
//                    inclusive = true
//                }
//            }
//        }) {
//            Text("Login")
//        }
//        Spacer(modifier = Modifier.height(30.dp))
//        Button(onClick = {
//            navController.navigate(AuthRouteScreen.SignUp.route)
//        }) {
//            Text("Don't have a account? Register")
//        }
//        Spacer(modifier = Modifier.height(30.dp))
//        Button(onClick = {
//            navController.navigate(AuthRouteScreen.Forget.route)
//        }) {
//            Text("Forgot Password?")
//        }
//    }
//}
//
//@Preview
//@Composable
//private fun LoginScreenPreview() {
//    LoginScreen(navController = rememberNavController())
//}