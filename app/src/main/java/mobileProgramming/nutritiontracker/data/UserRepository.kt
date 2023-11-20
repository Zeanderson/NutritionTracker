package mobileProgramming.nutritiontracker.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAllData: Flow<List<User>> = userDao.readAllData()

    @WorkerThread
    suspend fun addUser(user: User)
    {
        userDao.addUser(user)
    }

    @WorkerThread
    suspend fun getUserByEmail(email: String): LiveData<User?> {
        return userDao.getUserByEmail(email)
    }



}