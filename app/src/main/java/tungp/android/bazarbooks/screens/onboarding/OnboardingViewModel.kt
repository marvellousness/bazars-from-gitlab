package tungp.android.bazarbooks.screens.onboarding

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import tungp.android.bazarbooks.screens.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() :
    BaseViewModel<OnboardingState, OnboardingEvent>(initialState = OnboardingState()) {
        val TAG = "~~~OnboardingViewModel"

    override suspend fun handleActions() {
        pendingActions.collect { action ->
            when (action) {
                OnboardingEvent.GotoAuth -> {
                    // Navigate to login screen
                    Log.d(TAG, "handleActions: ON SKIP")
                }
            }
        }
    }

}

data class OnboardingState(
    var introItemList: List<IntroItem> = OnboardingData.introItemList(),
)

sealed class OnboardingEvent {
    object GotoAuth : OnboardingEvent()
}
