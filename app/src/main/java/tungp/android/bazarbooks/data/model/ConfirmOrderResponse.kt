package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.CartItem

@Serializable
data class ConfirmOrderResponse(
    val status: Boolean
)