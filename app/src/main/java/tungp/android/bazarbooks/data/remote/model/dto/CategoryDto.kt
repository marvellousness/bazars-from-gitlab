package tungp.android.bazarbooks.data.remote.model.dto

data class CategoryDto(
    val id: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val bookCount: Int = 0
)