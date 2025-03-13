package tungp.android.bazarbooks.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Base ViewModel for MVI architecture pattern.
 * 
 * @param STATE The type of data that will be wrapped in BaseViewState.Data
 * @param EVENT The type of events this ViewModel will handle
 */
abstract class MviViewModel<STATE, EVENT> : MvvmViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState<STATE>>(BaseViewState.Empty)
    val uiState: StateFlow<BaseViewState<STATE>> = _uiState.asStateFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: BaseViewState<STATE>) = safeLaunch {
        _uiState.emit(state)
    }

    protected fun setData(data: STATE) = safeLaunch {
        _uiState.emit(BaseViewState.Data(data))
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.value = BaseViewState.Error(exception)
    }
}