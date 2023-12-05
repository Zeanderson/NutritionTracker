package mobileProgramming.nutritiontracker.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.UserApplication
import mobileProgramming.nutritiontracker.data.User

class SignupActivity : AppCompatActivity() {

    private val signUpViewModel: SignupViewModel by viewModels {
        SignUpViewModelFactory((application as UserApplication).repository, -1)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

          // -------------------- \\
         //  Reference Calls       \\
        // ------------------------ \\
        val firstNameEditText = findViewById<EditText>(R.id.editFirstName)
        val lastNameEditText = findViewById<EditText>(R.id.editLastName)
        val emailEditText = findViewById<EditText>(R.id.editEmail)
        val passwordEditText = findViewById<EditText>(R.id.editPassword)
        val passwordReenterEditText = findViewById<EditText>(R.id.editPasswordReenter)
        val currentWeightEditText = findViewById<EditText>(R.id.editCurrentWeight)
        val goalWeightEditText = findViewById<EditText>(R.id.editWeightGoal)
        val goalCaloriesEditText = findViewById<EditText>(R.id.editCalorieGoal)

        // Button variables
        val submitButton = findViewById<Button>(R.id.buttonSignup)

        // OnClick Listeners
        submitButton.setOnClickListener{
            // -------- \\
            //  Inputs   \\
            // ---------- \\
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passwordReenter = passwordReenterEditText.text.toString()
            val currentWeight = currentWeightEditText.text.toString()
            val goalWeight = goalWeightEditText.text.toString()
            val goalCalories = goalCaloriesEditText.text.toString()

            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()
                || passwordReenter.isBlank() || currentWeight.isBlank() || goalWeight.isBlank() || goalCalories.isBlank()) {
                // Display a toast message if any field is empty
                Toast.makeText(this@SignupActivity, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else if(password != passwordReenter) {
                // Display a message if passwords don't match
                Toast.makeText(this@SignupActivity, "Please match passwords", Toast.LENGTH_SHORT).show()
            } else {
                // Create user with specified fields
                val newUser = User(
                    id = 0,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    currentWeight = currentWeight.toInt(),
                    weightGoal = goalWeight.toInt(),
                    calorieGoal = goalCalories.toInt()
                )
                CoroutineScope(SupervisorJob()).launch {
                    signUpViewModel.insert(newUser)
                }
                Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}