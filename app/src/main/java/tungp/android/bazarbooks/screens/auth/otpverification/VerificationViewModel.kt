package tungp.android.bazarbooks.screens.auth.otpverification

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(VerificationState())
    val state: StateFlow<VerificationState> = _state.asStateFlow()

    private val _content = MutableStateFlow<VerificationContent?>(null)
    val content: StateFlow<VerificationContent?> = _content.asStateFlow()

    init {
        savedStateHandle.get<String>("username")?.let {
            _state.value = state.value.copy(username = it)
        }
        savedStateHandle.get<String>("email")?.let {
            _state.value = state.value.copy(emailOrPhone = it)
        }
        savedStateHandle.get<String>("password")?.let {
            _state.value = state.value.copy(password = it)
        }
        savedStateHandle.get<String>("phone")?.let {
            _state.value = state.value.copy(phone = it)
        }
        loadContent()
    }

    private fun loadContent() {
        viewModelScope.launch {
            try {
                val inputStream: InputStream = context.assets.open("verification.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                _content.value = Json.decodeFromString(jsonString)
            } catch (e: Exception) {
                _state.value = state.value.copy(error = e.message)
            }
        }
    }

    fun onEvent(event: VerificationEvent) {
        when (event) {
            is VerificationEvent.CodeChanged -> {
                _state.value = state.value.copy(code = event.code)
            }
            VerificationEvent.VerifyCode -> {
                // Handle verify code
            }
            VerificationEvent.ResendCode -> {
                // Handle resend code
            }
            VerificationEvent.ResetState -> {
                _state.value = VerificationState()
            }
        }
    }
}