package tungp.android.bazarbooks.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.domain.model.Order

@Dao
interface OrderDao {

    @Upsert
    suspend fun upsertOrder(order: Order)

    @Query("SELECT * FROM `order` WHERE id = :id")
    fun getOrderById(id: String): Flow<Order>
}
