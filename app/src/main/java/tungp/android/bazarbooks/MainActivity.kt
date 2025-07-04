package tungp.android.bazarbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import tungp.android.bazarbooks.navigation.graphs.RootNavGraph
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.util.CredentialsStorage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var credentialsStorage: CredentialsStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Make the system UI draw over app content
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val credentials = credentialsStorage.getCredentials()
        val isAuth = credentials != null

        setContent {
            BazarTheme {
                RootNavGraph(isAuth = isAuth)
            }
        }
    }
}