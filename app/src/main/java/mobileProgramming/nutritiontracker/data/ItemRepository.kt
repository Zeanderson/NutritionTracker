package mobileProgramming.nutritiontracker.data

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {

    val readAllItem: Flow<List<Item>> = itemDao.getItems()

    fun getItem(id:Int):Flow<Item>{
        return itemDao.getItem(id)
    }

    @WorkerThread
    suspend fun addItem(item: Item)
    {
        Log.d("ItemRepo", "Inserting for ${item.itemName}  with ID ${item.id}")
        itemDao.addItem(item)
    }

    @WorkerThread
    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }
}