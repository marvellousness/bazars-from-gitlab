package tungp.android.bazarbooks.screens.order

sealed class OrderEvent {
    object LoadCartDetail : OrderEvent()
}