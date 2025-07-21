package tungp.android.bazarbooks.screens.order.location

import dagger.hilt.android.lifecycle.HiltViewModel
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor() : MviViewModel<LocationState, LocationEvent>() {

    override fun onTriggerEvent(eventType: LocationEvent) {

    }

}
