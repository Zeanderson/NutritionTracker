package mobileProgramming.nutritiontracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
//TODO need to have 4 objects (breakfast, lunch, dinner, & snack)
//TODO each one of these objects will have subfields defined
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    val password: String
) : Serializable


@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate=false)
    var id: Int, // Users ID, so we can tell whos is whos
    var type: String,
    var itemName: String,
    var itemCalories: Int
)
