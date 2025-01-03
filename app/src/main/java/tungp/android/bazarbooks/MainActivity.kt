package tungp.android.bazarbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import tungp.android.bazarbooks.navigation.graphs.RootNavGraph
import tungp.android.bazarbooks.ui.theme.BazarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BazarTheme {
                RootNavGraph(isAuth = false)
            }
        }
    }
}