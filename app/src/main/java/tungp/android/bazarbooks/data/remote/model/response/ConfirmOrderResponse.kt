package tungp.android.bazarbooks.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmOrderResponse(
    val status: Boolean
)