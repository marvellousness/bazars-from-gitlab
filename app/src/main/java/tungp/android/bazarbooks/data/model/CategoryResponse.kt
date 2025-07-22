package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val bookCount: Int = 0
)

@Serializable
data class CategoriesResponse(
    val categories: List<CategoryResponse> = emptyList()
) 