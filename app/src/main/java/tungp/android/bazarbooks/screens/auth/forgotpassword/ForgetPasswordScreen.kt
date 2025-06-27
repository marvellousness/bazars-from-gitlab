package tungp.android.bazarbooks.screens.auth.forgotpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Forgot Screen")
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text("Reset password")
        }
    }
}

@Preview
@Composable
private fun ForgetPasswordPreview() {
    ForgetPasswordScreen(navController = rememberNavController())
}