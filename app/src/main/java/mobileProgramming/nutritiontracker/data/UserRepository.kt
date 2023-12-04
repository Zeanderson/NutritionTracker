package mobileProgramming.nutritiontracker.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAllData: Flow<List<User>> = userDao.readAllData()

    @WorkerThread
    suspend fun addUser(user: User)
    {
        userDao.addUser(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUser(user: User)
    {
        userDao.update(user)
    }
    fun getUserById(id: Int): User {
        return userDao.getUserById(id)
    }
}