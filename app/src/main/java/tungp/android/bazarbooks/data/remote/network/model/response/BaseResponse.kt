package tungp.android.bazarbooks.data.remote.network.model.response

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    val responseCode = 0
    val message: String? = null
}