package tungp.android.bazarbooks.screens.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Action>(initialState: State) : ViewModel() {
    private val _events = MutableSharedFlow<Action>(
        extraBufferCapacity = 20,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val pendingActions = _events

    var state by mutableStateOf(initialState)
        protected set

    private val _state = MutableStateFlow(initialState)
    val stateX: StateFlow<State> = _state

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    fun onAction(action: Action) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    protected abstract suspend fun handleActions()
}