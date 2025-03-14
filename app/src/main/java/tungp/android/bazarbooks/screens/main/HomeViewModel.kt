package tungp.android.bazarbooks.screens.main

import dagger.hilt.android.lifecycle.HiltViewModel
import tungp.android.bazarbooks.domain.usecase.GetHomeFeedsUseCase
import tungp.android.bazarbooks.domain.usecase.NoParams
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeFeedsUseCase: GetHomeFeedsUseCase,
) : MviViewModel<HomeState, HomeEvent>() {
    override fun onTriggerEvent(eventType: HomeEvent) {
        when (eventType) {
            HomeEvent.LoadHomeFeeds -> onLoadHomeFeeds()
        }
    }

    private fun onLoadHomeFeeds() = safeLaunch {
        setState(BaseViewState.Loading)
        execute(getHomeFeedsUseCase(params = NoParams)) { dto ->
            setData(
                HomeState(
                    specialOffers = dto.specialOffers,
                    topOfWeeks = dto.topOfWeek,
                    bestVendors = dto.bestVendors,
                    authors = dto.authors
                )
            )
        }
    }
}
