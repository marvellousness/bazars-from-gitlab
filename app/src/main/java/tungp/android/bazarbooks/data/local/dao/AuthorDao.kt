package tungp.android.bazarbooks.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.data.local.entity.AuthorEntity

// Author DAO
@Dao
interface AuthorDao {
    @Query("SELECT * FROM authors ORDER BY name ASC")
    fun getAllAuthors(): Flow<List<AuthorEntity>>

    @Query("SELECT * FROM authors WHERE author_id = :authorId")
    suspend fun getAuthorById(authorId: String): AuthorEntity?

    @Query("SELECT * FROM authors WHERE name LIKE '%' || :query || '%'")
    fun searchAuthors(query: String): Flow<List<AuthorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: AuthorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthors(authors: List<AuthorEntity>)

    @Update
    suspend fun updateAuthor(author: AuthorEntity)

    @Delete
    suspend fun deleteAuthor(author: AuthorEntity)

    @Query("DELETE FROM authors")
    suspend fun deleteAll()
}