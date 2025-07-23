package tungp.android.bazarbooks.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.data.local.entity.BookEntity

@Dao
interface BookDao {

    @Query("SELECT * FROM books ORDER BY title ASC")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE book_id = :bookId")
    suspend fun getBookById(bookId: String): BookEntity?

    @Query("SELECT * FROM books WHERE author_id = :authorId")
    fun getBooksByAuthor(authorId: String): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE category_id = :categoryId")
    fun getBooksByCategory(categoryId: String): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchBooks(query: String): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE is_featured = 1 ORDER BY created_at DESC")
    fun getFeaturedBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE discount_price IS NOT NULL ORDER BY (price - discount_price) DESC")
    fun getBooksOnSale(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("DELETE FROM books")
    suspend fun deleteAll()
}