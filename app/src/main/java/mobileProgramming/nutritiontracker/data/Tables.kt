package mobileProgramming.nutritiontracker.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    val password: String,
    var currentWeight: Int,
    var weightGoal: Int,
    var calorieGoal: Int
) : Serializable


@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate=true)
    var id: Int?,
    var userId: Int,
    var type: String,
    var itemName: String,
    var itemCalories: Int,
    var itemProtein: Int,
    var itemCarbs: Int,
    var itemFat: Int,
    var itemFiber: Int,
    var itemWater: Int
)

