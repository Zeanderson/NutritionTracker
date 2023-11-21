package mobileProgramming.nutritiontracker.popups

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.UserApplication
import mobileProgramming.nutritiontracker.data.Item

class NewBreakfast: AppCompatActivity() {

    private val newBreakfastViewModel: NewBreakfastViewModel by viewModels {
        NewBreakfastViewModelFactory((application as UserApplication).itemRepository, -1)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_breakfast_item)

        val userId = intent.getIntExtra("id", -1)

        // -------------------- \\
        //  Reference Calls       \\
        // ------------------------ \\
        val itemNameEditText = findViewById<EditText>(R.id.newBreakfastName)
        val itemCaloriesEditText = findViewById<EditText>(R.id.newBreakfastCalories)

        // Button variables
        val submitButton = findViewById<Button>(R.id.button_save)

        //OnClick Listeners
        submitButton.setOnClickListener{
            // -------- \\
            //  Inputs   \\
            // ---------- \\
            val itemName = itemNameEditText.text.toString()
            val itemCalories = itemCaloriesEditText.text.toString()

            if (itemName.isBlank() || itemCalories.isBlank())
            {
                // Display toast that field is empty
                Toast.makeText(this@NewBreakfast, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val newItem = Item(
                    id = 1,
                    type = "breakfast",
                    itemName = itemName,
                    itemCalories = itemCalories.toInt()
                )
                CoroutineScope(SupervisorJob()).launch {
                    newBreakfastViewModel.addItem(newItem)
                }
                Toast.makeText(this@NewBreakfast, "Breakfast Item Added Succesfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

}