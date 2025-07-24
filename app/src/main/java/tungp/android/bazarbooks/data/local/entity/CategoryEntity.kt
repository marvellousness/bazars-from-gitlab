package tungp.android.bazarbooks.data.local.entity

import androidx.room.ColumnInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

// Category Entity
@Entity(tableName = "categories")
data class CategoryEntity(
    @ColumnInfo(name = "category_id")
    @PrimaryKey val categoryId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "parent_category_id")
    val parentCategoryId: String? = null,
    @ColumnInfo(name = "sort_order")
    val sortOrder: Int = 0,
)