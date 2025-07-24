package tungp.android.bazarbooks.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

// Book Entity
@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["author_id"],
            childColumns = ["author_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["author_id"]),
        Index(value = ["category_id"]),
        Index(value = ["isbn"]),
        Index(value = ["title"])
    ]
)
data class BookEntity(
    @ColumnInfo(name = "book_id")
    @PrimaryKey val bookId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author_id")
    val authorId: String,
    @ColumnInfo(name = "category_id")
    val categoryId: String? = null,
    @ColumnInfo(name = "isbn")
    val isbn: String? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "cover_image_url")
    val coverImageUrl: String? = null,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "discount_price")
    val discountPrice: Double? = null,
    @ColumnInfo(name = "language")
    val language: String = "English",
    @ColumnInfo(name = "published_date")
    val publishedDate: Date? = null,
    @ColumnInfo(name = "publisher")
    val publisher: String? = null,
    @ColumnInfo(name = "format")
    val format: String = "Physical", // Physical, Digital, Audiobook
    @ColumnInfo(name = "stock_quantity")
    val stockQuantity: Int = 0,
    @ColumnInfo(name = "average_rating")
    val averageRating: Float = 0.0f,
    @ColumnInfo(name = "is_featured")
    val isFeatured: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date,
)