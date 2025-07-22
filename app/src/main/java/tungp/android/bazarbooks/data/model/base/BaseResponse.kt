package tungp.android.bazarbooks.data.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val statusCode: Int,
    val statusMessage: String,
    val data: T?
)