package mobileProgramming.nutritiontracker.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.UserApplication
import mobileProgramming.nutritiontracker.main.MainActivity
import mobileProgramming.nutritiontracker.signup.SignupActivity

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((application as UserApplication).repository, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // -------------------- \\
        //  Reference Calls       \\
        // ------------------------ \\
        val emailEditText = findViewById<EditText>(R.id.editEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)

        // Button variables
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val signupButton = findViewById<Button>(R.id.buttonSignup)

        // OnClick Listeners
        loginButton.setOnClickListener {
            // -------- \\
            //  Inputs   \\
            // ---------- \\
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            var successful = false

            loginViewModel.allUserData.observe(this) { user ->
                user?.let{
                    for (user in it)
                    {
                        if (email == user.email && password == user.password)
                        {
                            Toast.makeText(this@LoginActivity, "Welcome ${user.firstName} ${user.lastName}", Toast.LENGTH_SHORT).show()
                            successful = true
                            val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(mainIntent)
                            finish()
                        }
                    }

                    if (!successful)
                    {
                        Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        signupButton.setOnClickListener {
            val signupIntent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(signupIntent)
        }
    }
}
