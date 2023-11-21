package mobileProgramming.nutritiontracker.popups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.coroutineScope
import mobileProgramming.nutritiontracker.data.Item
import mobileProgramming.nutritiontracker.data.ItemRepository
import java.lang.IllegalArgumentException

class NewBreakfastViewModel(private val repository: ItemRepository, private val id:Int) : ViewModel() {
    // ------------------------------- \\
    //  Repository Calls               \\
    // ------------------------------- \\
    suspend fun addItem(item: Item) {
        coroutineScope {
            repository.addItem(item)
        }
    }

    suspend fun updateItem(item: Item) {
        coroutineScope {
            repository.updateItem(item)
        }
    }
}
class NewBreakfastViewModelFactory(private val repository: ItemRepository, private val id: Int) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewBreakfastViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return NewBreakfastViewModel(repository,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}