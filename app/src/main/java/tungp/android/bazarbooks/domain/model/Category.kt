package tungp.android.bazarbooks.domain.model

data class Category(
    val id: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val bookCount: Int = 0
)

data class Categories(
    val categories: List<Category> = emptyList()
) 