package mobileProgramming.nutritiontracker.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.data.UserViewModel

class SignupActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Button variables
        val submitButton = findViewById<Button>(R.id.buttonSignup)

        // OnClick Listeners
        submitButton.setOnClickListener{
            addUser()
        }
    }

    private fun addUser() {
        val newUser = User(
            id = 0,
            firstName = "Zack",
            lastName = "Anderson",
            email = "zeanders@uark.edu",
            password = "password"
        )
        userViewModel.addUser(newUser)
    }
}