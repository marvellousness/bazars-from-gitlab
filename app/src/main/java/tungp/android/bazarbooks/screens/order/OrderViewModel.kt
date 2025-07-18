package tungp.android.bazarbooks.screens.order

import dagger.hilt.android.lifecycle.HiltViewModel
import tungp.android.bazarbooks.domain.usecase.ConfirmOrderUseCase
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val confirmOrderUseCase: ConfirmOrderUseCase,
) : MviViewModel<OrderState, OrderEvent>() {
    val paymentMethodItems = OrderSampleData.paymentMethodItems
    val paymentDetails = OrderSampleData.paymentDetails

    override fun onTriggerEvent(eventType: OrderEvent) {

    }

} 