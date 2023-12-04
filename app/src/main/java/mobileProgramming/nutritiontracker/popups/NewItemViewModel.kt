package mobileProgramming.nutritiontracker.popups

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.coroutineScope
import mobileProgramming.nutritiontracker.data.Item
import mobileProgramming.nutritiontracker.data.ItemRepository
import java.lang.IllegalArgumentException

class NewItemViewModel(private val repository: ItemRepository, private val id:Int) : ViewModel() {
    // ------------------------------- \\
    //  Repository Calls               \\
    // ------------------------------- \\
    suspend fun addItem(item: Item) {
        coroutineScope {
            repository.addItem(item)
        }
    }

    suspend fun updateItem(item: Item)
    {
        coroutineScope {
            repository.updateItem(item)
        }
    }

    var curItem: LiveData<Item> = repository.getItem(id).asLiveData()

    fun updateId(id:Int) {
        curItem = repository.getItem(id).asLiveData()
    }

    suspend fun deleteItem(id: Int)
    {
        coroutineScope{
            repository.deleteItem(id)
        }
    }
}
class NewItemViewModelFactory(private val repository: ItemRepository, private val id: Int) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewItemViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return NewItemViewModel(repository,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}