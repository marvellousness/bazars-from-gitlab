package tungp.android.bazarbooks.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "users",
    indices = [
        Index(value = ["user_id"])
    ]
)
data class UserEntity(
    @ColumnInfo(name = "user_id")
    @PrimaryKey
    val userId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "phone")
    val phone: String? = null,
)