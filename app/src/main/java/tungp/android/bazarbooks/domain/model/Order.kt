package tungp.android.bazarbooks.domain.model

data class Order(
    val id: String,
    val userId: String,
    val bookId: String,
    val quantity: Int,
    val status: String,
)
