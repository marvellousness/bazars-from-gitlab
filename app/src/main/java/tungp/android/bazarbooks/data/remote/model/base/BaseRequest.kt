package tungp.android.bazarbooks.data.remote.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseRequest(
    val apiKey: String? = null,
    val language: String? = null
    // Add other common request parameters
)