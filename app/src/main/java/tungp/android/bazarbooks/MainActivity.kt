package tungp.android.bazarbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import tungp.android.bazarbooks.navigation.graphs.RootNavGraph
import tungp.android.bazarbooks.ui.theme.BazarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Make the system UI draw over app content
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BazarTheme {
                RootNavGraph(isAuth = false)
            }
        }
    }
}