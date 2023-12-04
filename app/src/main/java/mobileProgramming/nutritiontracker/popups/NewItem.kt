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

const val EXTRA_ID:String = "mobileProgramming.nutritiontracker.EXTRA_ID"
class NewItem: AppCompatActivity() {

    private lateinit var itemNameEditText: EditText
    private lateinit var itemCaloriesEditText: EditText
    private lateinit var itemProteinEditText: EditText
    private lateinit var itemCarbsEditText: EditText
    private lateinit var itemFatEditText: EditText
    private lateinit var itemFiberEditText: EditText
    private lateinit var itemWaterEditText: EditText

    private val newItemViewModel: NewItemViewModel by viewModels {
        NewItemViewModelFactory((application as UserApplication).itemRepository, -1)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)

        // References
        itemNameEditText = findViewById(R.id.newItemName)
        itemCaloriesEditText = findViewById(R.id.newItemCalories)
        itemProteinEditText = findViewById(R.id.newItemProtein)
        itemCarbsEditText = findViewById(R.id.newItemCarbs)
        itemFatEditText = findViewById(R.id.newItemFat)
        itemFiberEditText = findViewById(R.id.newItemFiber)
        itemWaterEditText = findViewById(R.id.newItemWater)



        // Extra
        val userId = intent.getIntExtra("UserID",-1)
        var itemType = "Food"
        val extraType = intent.getStringExtra("type")
        if (extraType != null) {
            itemType = extraType!!
        } else {
            Log.d("NewItem", "Missing Item Type")
        }

        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1)
        {
            newItemViewModel.updateId(id)
        }

        // If clicked into, updates the UI to show item with ID
        newItemViewModel.curItem.observe(this) {
            item -> item?.let {
                itemNameEditText.setText(item.itemName)
                itemCaloriesEditText.setText(item.itemCalories.toString())
                itemProteinEditText.setText(item.itemProtein.toString())
                itemCarbsEditText.setText(item.itemCarbs.toString())
                itemFatEditText.setText(item.itemFat.toString())
                itemFiberEditText.setText(item.itemFiber.toString())
                itemWaterEditText.setText(item.itemWater.toString())
        }
        }

//      Button variables
        val submitButton = findViewById<Button>(R.id.button_save)
        submitButton.text = "Add $itemType Item"

        val deleteButton = findViewById<Button>(R.id.button_delete)

        submitButton.setOnClickListener {
            // -------- \\
            //  Inputs   \\
            // ---------- \\
            val itemName = itemNameEditText.text.toString()
            val itemCalories = itemCaloriesEditText.text.toString()
            val itemProtein = itemProteinEditText.text.toString()
            val itemCarbs = itemCarbsEditText.text.toString()
            val itemFat = itemFatEditText.text.toString()
            val itemFiber = itemFiberEditText.text.toString()
            val itemWater = itemWaterEditText.text.toString()

            if (itemName.isBlank() || itemCalories.isBlank() || itemProtein.isBlank() || itemCarbs.isBlank() || itemFat.isBlank() || itemFiber.isBlank() || itemWater.isBlank()) {
                Toast.makeText(this@NewItem, "Please fill out all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
            CoroutineScope(SupervisorJob()).launch {
                if (id == -1) {
                    newItemViewModel.addItem(
                        Item(
                            null,
                            userId,
                            itemType,
                            itemName,
                            itemCalories.toInt(),
                            itemProtein.toInt(),
                            itemCarbs.toInt(),
                            itemFat.toInt(),
                            itemFiber.toInt(),
                            itemWater.toInt()
                        )
                    )
                    runOnUiThread {
                        Toast.makeText(
                            this@NewItem,
                            "$itemType Item Added Succesfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setResult(RESULT_OK)
                    finish()
                } else {
                    newItemViewModel.updateItem(
                        Item(
                            id,
                            userId,
                            itemType,
                            itemName,
                            itemCalories.toInt(),
                            itemProtein.toInt(),
                            itemCarbs.toInt(),
                            itemFat.toInt(),
                            itemFiber.toInt(),
                            itemWater.toInt()
                        )
                    )
                    runOnUiThread {
                        Toast.makeText(
                            this@NewItem,
                            "$itemType Item Updated Succesfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }
        }

        deleteButton.setOnClickListener {
            if (id == -1)
            {
                setResult(RESULT_CANCELED)
                finish()
            } else {
                CoroutineScope(SupervisorJob()).launch {
                    newItemViewModel.deleteItem(id)
                }
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}