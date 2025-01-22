package tungp.android.bazarbooks

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import tungp.android.bazarbooks.domain.usecase.GetHomeFeedsUseCase
import tungp.android.bazarbooks.domain.usecase.NoParams
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHomeFeedsUseCase: GetHomeFeedsUseCase,
) : MviViewModel<BaseViewState<MainViewState>, MainViewEvent>() {

    override fun onTriggerEvent(eventType: MainViewEvent) {
        when (eventType) {
            MainViewEvent.LoadHomeFeeds -> onLoadHomeFeeds()
        }
    }

    private fun onLoadHomeFeeds() = safeLaunch {
        setState(BaseViewState.Loading)
        execute(getHomeFeedsUseCase(params = NoParams)) { dto ->
            val topOfWeek = dto.topOfWeek

            Log.d(TAG, "onLoadHomeFeeds: DATA SIZE=${topOfWeek.size}")
            setState(
                BaseViewState.Data(
                    MainViewState(
                        topOfWeek = dto.topOfWeek
                    )
                )
            )
        }
    }

    companion object {
        private const val TAG = "~~~MainModel"
    }
}

