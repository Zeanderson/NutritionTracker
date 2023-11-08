package mobileProgramming.nutritiontracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
//TODO need to have 4 objects (breakfast, lunch, dinner, & snack)
//TODO each one of these objects will have subfields defined
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)