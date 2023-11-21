package mobileProgramming.nutritiontracker

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import mobileProgramming.nutritiontracker.data.ItemDatabase
import mobileProgramming.nutritiontracker.data.ItemRepository
import mobileProgramming.nutritiontracker.data.UserDatabase
import mobileProgramming.nutritiontracker.data.UserRepository

class UserApplication : Application() {
//    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { UserDatabase.getDatabase(this)}
    val repository by lazy {UserRepository(database.userDao())}
    val itemDatabase by lazy {ItemDatabase.getDatabase(this)}
    val itemRepository by lazy {ItemRepository(itemDatabase.itemDao())}
}