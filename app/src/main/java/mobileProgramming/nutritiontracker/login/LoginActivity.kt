package mobileProgramming.nutritiontracker.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.main.MainActivity
import mobileProgramming.nutritiontracker.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Button variables
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val signupButton = findViewById<Button>(R.id.buttonSignup)

        // OnClick Listeners
        loginButton.setOnClickListener{
            val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(mainIntent)
        }

        signupButton.setOnClickListener{
            val signupIntent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(signupIntent)
        }
    }
}