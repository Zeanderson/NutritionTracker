package mobileProgramming.nutritiontracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // Insert a user
    @Insert(onConflict = OnConflictStrategy.IGNORE)  // If you try to add same user, just ignore
    suspend fun addUser(user: User)

    // Getting user data for login
    @Query("SELECT * FROM user_table WHERE email = :email")
    fun getUserByEmail(email: String): LiveData<User?>

    // Read all user data
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): Flow<List<User>>
}