package tungp.android.bazarbooks.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Author Entity
@Entity(tableName = "authors")
data class AuthorEntity(
    @PrimaryKey @ColumnInfo(name = "author_id") val authorId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "biography") val biography: String? = null,
    @ColumnInfo(name = "profile_image_url") val profileImageUrl: String? = null,
    @ColumnInfo(name = "nationality") val nationality: String? = null,
    @ColumnInfo(name = "birth_date") val birthDate: Date? = null,
)