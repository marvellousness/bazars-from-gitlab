package tungp.android.bazarbooks.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class MvvmViewModel : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    open fun handleError(exception: Throwable) {}

    open fun startLoading() {}

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { handleError(it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    protected suspend fun <T> execute(
        callFlow: Flow<Result<T>>,
        completionHandler: (collect: T) -> Unit = {},
    ) {
        callFlow
            .onStart { startLoading() }
            .catch { handleError(it) }
            .collect { state ->
                state.onSuccess { completionHandler.invoke(it) }
                state.onFailure {
                    handleError(it)
                }
            }
    }

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-ExceptionHandler"
    }
}