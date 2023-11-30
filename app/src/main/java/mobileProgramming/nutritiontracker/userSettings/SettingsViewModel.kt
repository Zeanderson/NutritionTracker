package mobileProgramming.nutritiontracker.userSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mobileProgramming.nutritiontracker.data.UserRepository
import mobileProgramming.nutritiontracker.signup.SignupViewModel
import java.lang.IllegalArgumentException

class SettingsViewModel (private val repository: UserRepository, private val id: Int): ViewModel() {
    // ------------------------------- \\
    //  Repository Calls               \\
    // ------------------------------- \\
}

class SettingsViewModelFactory(private val repository: UserRepository, private val id:Int): ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return SignupViewModel(repository,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}