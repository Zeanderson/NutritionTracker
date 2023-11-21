package mobileProgramming.nutritiontracker.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.UserApplication
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.popups.NewBreakfast

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as UserApplication).repository, -1)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -------------------- \\
        //  Reference Calls       \\
        // ------------------------ \\
        var userTextView = findViewById<TextView>(R.id.userLayoutText)

        // Mock User
        var mainUser = User(-1,"","","","")
        var successful = false // Boolean for loop

        val userId = intent.getIntExtra("userId", -1)
        if (userId != null)
        {
            // Checking to get user data from ID
            mainViewModel.allUserData.observe(this) { user ->
                user?.let{
                    for (user in it)
                    {
                        if (userId == user.id)
                        {
                            successful = true
                            mainUser.id = user.id
                            mainUser.firstName = user.firstName
                            mainUser.lastName = user.lastName
                            mainUser.email = user.email

                            userTextView.text = "Hello, ${mainUser.firstName}"
                        }
                    }

                    if (!successful)
                    {
                        Toast.makeText(this@MainActivity, "User Not Found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            // This is bad :)
            Toast.makeText(this, "User information not found", Toast.LENGTH_SHORT).show()
            finish()
        }
        // Button variables
        val breakfastAdd = findViewById<FloatingActionButton>(R.id.breakfestBoxAdd)

        // OnClick Listeners
        breakfastAdd.setOnClickListener{
            val breakfastIntent = Intent(this@MainActivity,NewBreakfast::class.java)
            breakfastIntent.putExtra("id",userId)
            startActivity(breakfastIntent)
        }
    }
}