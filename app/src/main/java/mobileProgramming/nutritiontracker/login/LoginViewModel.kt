package mobileProgramming.nutritiontracker.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.data.UserRepository
import java.lang.IllegalArgumentException

class LoginViewModel(private val repository: UserRepository, private val id:Int): ViewModel() {
    // ------------------------------- \\
    //  Repository Calls               \\
    // ------------------------------- \\

    val allUserData: LiveData<List<User>> = repository.readAllData.asLiveData()
}

class LoginViewModelFactory(private val repository: UserRepository, private val id:Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}