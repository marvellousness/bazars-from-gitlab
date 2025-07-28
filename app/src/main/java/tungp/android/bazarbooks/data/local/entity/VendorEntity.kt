package tungp.android.bazarbooks.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendors")
data class VendorEntity(
    @PrimaryKey
    @ColumnInfo(name = "vendor_id")
    val vendorId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "cover")
    val cover: String,
    @ColumnInfo(name = "author_name")
    val authorName: String,
    @ColumnInfo(name = "books_count")
    val booksCount: Int,
    @ColumnInfo(name = "rating")
    val rating: Int,
    @ColumnInfo(name = "category")
    val category: String = "Books", // Default category
)