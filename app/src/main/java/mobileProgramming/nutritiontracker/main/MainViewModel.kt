package mobileProgramming.nutritiontracker.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.coroutineScope
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.data.UserRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val repository: UserRepository, private val id:Int): ViewModel() {
    // ------------------------------- \\
    //  Repository Calls               \\
    // ------------------------------- \\
    val allUserData: LiveData<List<User>> = repository.readAllData.asLiveData()

}

class MainViewModelFactory(private val repository: UserRepository, private val id:Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}