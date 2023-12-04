package mobileProgramming.nutritiontracker.userSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.coroutineScope
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.data.UserRepository
import java.lang.IllegalArgumentException

class SettingsViewModel (private val repository: UserRepository): ViewModel() {
    // ------------------------------- \\
    //  Repository Calls               \\
    // ------------------------------- \\
    private lateinit var user: User

    suspend fun updateUser(updatedUser: User){
        coroutineScope {
            repository.updateUser(updatedUser)
        }
    }

    suspend fun getUser(userId: Int) : User {
        coroutineScope {
            user = repository.getUserById(userId)
        }
        return user
    }

}

class SettingsViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}