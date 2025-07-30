package tungp.android.bazarbooks.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tungp.android.bazarbooks.data.local.dao.AuthorDao
import tungp.android.bazarbooks.data.local.dao.BookDao
import tungp.android.bazarbooks.data.local.dao.CategoryDao
import tungp.android.bazarbooks.data.local.entity.AuthorEntity
import tungp.android.bazarbooks.data.local.entity.BookEntity
import tungp.android.bazarbooks.data.local.entity.CategoryEntity
import tungp.android.bazarbooks.data.local.entity.OrderEntity
import tungp.android.bazarbooks.data.local.entity.UserEntity
import tungp.android.bazarbooks.data.local.entity.VendorEntity

@Database(
    entities = [
        AuthorEntity::class,
        CategoryEntity::class,
        BookEntity::class,
        VendorEntity::class,
        UserEntity::class,
        OrderEntity::class
    ],
    version = BazarBooksDatabase.Companion.LATEST_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BazarBooksDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
    abstract fun categoryDao(): CategoryDao
    abstract fun bookDao(): BookDao

    companion object {
        const val DATABASE_NAME = "book_database"
        const val LATEST_VERSION = 1
    }
}