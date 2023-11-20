package mobileProgramming.nutritiontracker.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.coroutineScope
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.data.UserRepository
import java.lang.IllegalArgumentException

class SignupViewModel (private val repository: UserRepository, private val id: Int) : ViewModel() {

    // ------------------------------- \\
    //  Repository Calls               \\
    // ------------------------------- \\
    suspend fun insert(user: User) {
        coroutineScope {
            repository.addUser(user)
        }
    }
}

class SignUpViewModelFactory(private val repository: UserRepository, private val id:Int): ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return SignupViewModel(repository,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

