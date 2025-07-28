package tungp.android.bazarbooks.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {
    @Volatile
    private var INSTANCE: BazarBooksDatabase? = null

    private val roomDatabaseCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Insert initial data if needed
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }
    }

    fun getDatabase(context: Context): BazarBooksDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BazarBooksDatabase::class.java,
                BazarBooksDatabase.DATABASE_NAME
            ).addCallback(roomDatabaseCallback)
                .build()
            INSTANCE = instance
            instance
        }
    }

    private suspend fun populateDatabase(database: BazarBooksDatabase) {
        // Clear existing data (optional, but good for testing)
    }
}