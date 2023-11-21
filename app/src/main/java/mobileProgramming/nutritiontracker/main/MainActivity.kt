package mobileProgramming.nutritiontracker.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.UserApplication
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.popups.NewItem

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
        val breakfastAdd = findViewById<FloatingActionButton>(R.id.breakfastBoxAdd)
        val lunchAdd = findViewById<FloatingActionButton>(R.id.lunchBoxAdd)
        val dinnerAdd = findViewById<FloatingActionButton>(R.id.dinnerBoxAdd)
        val snackAdd = findViewById<FloatingActionButton>(R.id.snackBoxAdd)

        // OnClick Listeners
        breakfastAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("id",userId)
            itemIntent.putExtra("type", "Breakfast")
            startActivity(itemIntent)
        }
        lunchAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("id",userId)
            itemIntent.putExtra("type", "Lunch")
            startActivity(itemIntent)
        }
        dinnerAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("id",userId)
            itemIntent.putExtra("type", "Dinner")
            startActivity(itemIntent)
        }
        snackAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("id",userId)
            itemIntent.putExtra("type", "Snack")
            startActivity(itemIntent)
        }
    }
}