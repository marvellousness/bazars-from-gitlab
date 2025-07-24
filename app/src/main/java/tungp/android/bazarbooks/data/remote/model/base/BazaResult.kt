package tungp.android.bazarbooks.data.remote.model.base

sealed class BazaResult<out T> {
    data class Success<T>(val data: T) : BazaResult<T>()
    data class Error(val exception: Throwable, val code: Int? = null, val message: String? = null) :
        BazaResult<Nothing>()

    object Loading : BazaResult<Nothing>()
}
