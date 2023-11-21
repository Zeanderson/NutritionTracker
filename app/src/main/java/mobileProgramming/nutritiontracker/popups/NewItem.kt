package mobileProgramming.nutritiontracker.popups

import android.os.Bundle
import android.util.Log
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

class NewItem: AppCompatActivity() {

    private val newItemViewModel: NewItemViewModel by viewModels {
        NewItemViewModelFactory((application as UserApplication).itemRepository, -1)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)
        var itemType = "Food"
        val userId = intent.getIntExtra("id", -1)
        val itemExtra = intent.getStringExtra("type")
        if (itemExtra != null) {
            itemType = itemExtra!!
        } else {
            Log.d("NewItem", "Missing Item Type")
        }

        // -------------------- \\
        //  Reference Calls       \\
        // ------------------------ \\
        val itemNameEditText = findViewById<EditText>(R.id.newItemName)
        val itemCaloriesEditText = findViewById<EditText>(R.id.newItemCalories)

        // Button variables
        val submitButton = findViewById<Button>(R.id.button_save)
        submitButton.text = "Add $itemType Item"

        //OnClick Listeners
        submitButton.setOnClickListener{
            // -------- \\
            //  Inputs   \\
            // ---------- \\
            val itemName = itemNameEditText.text.toString()
            val itemCalories = itemCaloriesEditText.text.toString()

            if (itemName.isBlank() || itemCalories.isBlank()) {
                // Display toast that field is empty
                Toast.makeText(this@NewItem, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val newItem = Item(
                    id = 1,
                    type = itemType,
                    itemName = itemName,
                    itemCalories = itemCalories.toInt()
                )
                CoroutineScope(SupervisorJob()).launch {
                    newItemViewModel.addItem(newItem)
                }
                Toast.makeText(this@NewItem, "$itemType Item Added Succesfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}