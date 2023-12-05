package mobileProgramming.nutritiontracker.userSettings

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
import mobileProgramming.nutritiontracker.data.User

class SettingsActivity : AppCompatActivity() {

    private lateinit var editFirstName: EditText
    private lateinit var editLastName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editCurrentWeight: EditText
    private lateinit var editWeightGoal: EditText
    private lateinit var editCalorieGoal: EditText
    private lateinit var submitButton: Button
    private lateinit var currentUser: User

    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory((application as UserApplication).repository)
    }
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.user_settings)

        // -------------------- \\
        //  Reference Calls       \\
        // ------------------------ \\
        editFirstName = findViewById(R.id.editFirstName)
        editLastName = findViewById(R.id.editLastName)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editCurrentWeight = findViewById(R.id.editCurrentWeight)
        editWeightGoal = findViewById(R.id.editWeightGoal)
        editCalorieGoal = findViewById(R.id.editCalorieGoal)
        val userID = intent.getIntExtra("UserID", -1)

        CoroutineScope(SupervisorJob()).launch {
            currentUser = settingsViewModel.getUser(userID)
            editFirstName.setText(currentUser.firstName)
            editLastName.setText(currentUser.lastName)
            editEmail.setText(currentUser.email)
            editPassword.setText(currentUser.password)
            editCurrentWeight.setText(currentUser.currentWeight.toString())
            editWeightGoal.setText(currentUser.weightGoal.toString())
            editCalorieGoal.setText(currentUser.calorieGoal.toString())
        }

        // Button variables
        submitButton = findViewById(R.id.buttonUpdate)

        // OnClick Listeners
        submitButton.setOnClickListener {
            val firstName = editFirstName.text.toString()
            val lastName = editLastName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            var currentWeight = 195
            var weightGoal = 185
            var calorieGoal = 1900
            try {
                currentWeight = editCurrentWeight.text.toString().toInt()
                weightGoal = editWeightGoal.text.toString().toInt()
                calorieGoal = editCalorieGoal.text.toString().toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(this@SettingsActivity, "Invalid goal format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
                // Display a toast message if any field is empty
                Toast.makeText(this@SettingsActivity, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Update user with specified fields
                val newUser = User(
                    id = userID,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    currentWeight = currentWeight,
                    weightGoal = weightGoal,
                    calorieGoal = calorieGoal
                )
                CoroutineScope(SupervisorJob()).launch {
                    settingsViewModel.updateUser(newUser)
                }
                Toast.makeText(this, "Settings updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // OnClick Listeners
    }


}