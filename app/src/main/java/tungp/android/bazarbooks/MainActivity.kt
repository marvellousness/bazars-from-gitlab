package tungp.android.bazarbooks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import tungp.android.bazarbooks.components.EmptyView
import tungp.android.bazarbooks.components.ErrorView
import tungp.android.bazarbooks.components.LoadingView
import tungp.android.bazarbooks.extension.cast
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.navigation.graphs.RootNavGraph
import tungp.android.bazarbooks.ui.theme.BazarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BazarTheme {
                RootNavGraph(isAuth = true)

//                val mainViewModel = hiltViewModel<MainViewModel>()
//                val uiState by mainViewModel.uiState.collectAsState()

//                LaunchedEffect(key1 = Unit) {
//                    mainViewModel.onTriggerEvent(MainViewEvent.LoadHomeFeeds)
//                }
//
//                when (uiState) {
//                    is BaseViewState.Data -> {
//                        val data = uiState.cast<BaseViewState.Data<MainViewState>>().value
//                        Log.d(TAG, "onCreate: ${Gson().toJson(data)}")
//
//                    }
//
//                    BaseViewState.Empty -> EmptyView()
//                    is BaseViewState.Error -> ErrorView(
//                        e = uiState.cast<BaseViewState.Error>().throwable,
//                        action = {}
//                    )
//
//                    BaseViewState.Loading -> LoadingView()
//                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}