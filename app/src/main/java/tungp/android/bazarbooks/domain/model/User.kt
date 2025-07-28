package tungp.android.bazarbooks.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: String,
    val email: String,
    val address: String,
    val phone: String
)
