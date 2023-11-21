package mobileProgramming.nutritiontracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
@Dao
interface ItemDao {
    // Insert a item
    @Insert(onConflict = OnConflictStrategy.IGNORE)  // If you try to add same user, just ignore
    suspend fun addItem(item: Item)

    //Update a single word
    @Update
    suspend fun updateItem(item: Item):Int
}