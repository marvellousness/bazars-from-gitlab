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
    private var INSTANCE: BookDatabase? = null

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

    fun getDatabase(context: Context): BookDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BookDatabase::class.java,
                BookDatabase.DATABASE_NAME
            ).addCallback(roomDatabaseCallback)
                .build()
            INSTANCE = instance
            instance
        }
    }

    private suspend fun populateDatabase(database: BookDatabase) {
        // Clear existing data (optional, but good for testing)
    }
}