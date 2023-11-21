package mobileProgramming.nutritiontracker.data

import androidx.annotation.WorkerThread

class ItemRepository(private val itemDao: ItemDao) {

    @WorkerThread
    suspend fun addItem(item: Item)
    {
        itemDao.addItem(item)
    }

    @WorkerThread
    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }
}