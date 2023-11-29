package mobileProgramming.nutritiontracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    // Insert a item
    @Insert(onConflict = OnConflictStrategy.IGNORE)  // If you try to add same user, just ignore
    suspend fun addItem(item: Item)

    //Update a single word
    @Update
    suspend fun updateItem(item: Item):Int

    @Query("SELECT * FROM item_table WHERE id=:id")
    fun getItem(id:Int): Flow<Item>

    @Query("SELECT * FROM item_table ORDER BY itemName ASC")
    fun getItems(): Flow<List<Item>>
}