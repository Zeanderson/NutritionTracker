package mobileProgramming.nutritiontracker.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.UserApplication
import mobileProgramming.nutritiontracker.data.User
import mobileProgramming.nutritiontracker.popups.EXTRA_ID
import mobileProgramming.nutritiontracker.popups.NewItem
import mobileProgramming.nutritiontracker.userSettings.SettingsActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as UserApplication).repository, (application as UserApplication).itemRepository,-1)
    }

    val startNewItemActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK)
        {
            Log.d("MainActivity","Completed")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recyclers
        val breakfastRecyclerView = findViewById<RecyclerView>(R.id.breakfastRecyclerView)
        val breakfastAdapter = BreakfastListAdapter {
            //Then create a new intent with the ID of the word
            val intent = Intent(this@MainActivity, NewItem::class.java)
            intent.putExtra(EXTRA_ID,it.id)
            //And start the activity through the results contract
            startNewItemActivity.launch(intent)
        }
        breakfastRecyclerView.adapter = breakfastAdapter
        breakfastRecyclerView.layoutManager = LinearLayoutManager(this)

        val lunchRecyclerView = findViewById<RecyclerView>(R.id.lunchRecyclerView)
        val lunchAdapter = LunchListAdapter {
            //Then create a new intent with the ID of the word
            val intent = Intent(this@MainActivity, NewItem::class.java)
            intent.putExtra(EXTRA_ID,it.id)
            //And start the activity through the results contract
            startNewItemActivity.launch(intent)
        }
        lunchRecyclerView.adapter = lunchAdapter
        lunchRecyclerView.layoutManager = LinearLayoutManager(this)

        val dinnerRecyclerView = findViewById<RecyclerView>(R.id.dinnerRecyclerView)
        val dinnerAdapter = DinnerListAdapter {
            //Then create a new intent with the ID of the word
            val intent = Intent(this@MainActivity, NewItem::class.java)
            intent.putExtra(EXTRA_ID,it.id)
            //And start the activity through the results contract
            startNewItemActivity.launch(intent)
        }
        dinnerRecyclerView.adapter = dinnerAdapter
        dinnerRecyclerView.layoutManager = LinearLayoutManager(this)

        val snackRecyclerView = findViewById<RecyclerView>(R.id.snackRecyclerView)
        val snackAdapter = SnackListAdapter {
            //Then create a new intent with the ID of the word
            val intent = Intent(this@MainActivity, NewItem::class.java)
            intent.putExtra(EXTRA_ID,it.id)
            //And start the activity through the results contract
            startNewItemActivity.launch(intent)
        }
        snackRecyclerView.adapter = snackAdapter
        snackRecyclerView.layoutManager = LinearLayoutManager(this)

        // Data & Time information
        val dateAndTimeTextView: TextView = findViewById(R.id.userLayoutDate)

        val dateFormat = SimpleDateFormat("MMMM d", Locale.getDefault())
        val currentDateAndTime: String = dateFormat.format(Date())

        dateAndTimeTextView.text = currentDateAndTime

        // -------------------- \\
        //  Reference Calls       \\
        // ------------------------ \\
        var userTextView = findViewById<TextView>(R.id.userLayoutText)
        var goalsTextView = findViewById<TextView>(R.id.totalTrackingLayoutText)
        var calTextView = findViewById<TextView>(R.id.calorieBoxStatus)
        var proteinTextView = findViewById<TextView>(R.id.proteinBoxStatus)
        var carbsTextView = findViewById<TextView>(R.id.carbBoxStatus)
        var fatTextView = findViewById<TextView>(R.id.fatBoxStatus)
        var fiberTextView = findViewById<TextView>(R.id.fiberBoxStatus)
        var waterTextView = findViewById<TextView>(R.id.waterBoxStatus)
        // Mock User
        var mainUser = User(-1,"","","","", 0, 0, 0)
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
                            mainUser.currentWeight = user.currentWeight
                            mainUser.weightGoal = user.weightGoal
                            mainUser.calorieGoal = user.calorieGoal
                        }
                    }

                    if (!successful)
                    {
                        Toast.makeText(this@MainActivity, "User Not Found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "User information not found", Toast.LENGTH_SHORT).show()
            finish()
        }

        mainViewModel.allItemData.observe(this) {
            items ->
            items.let {
                // Updates the recycler views
                val filteredBreakfastItems = items.filter { it.userId == mainUser.id && it.type == "Breakfast" }
                breakfastAdapter.submitList(filteredBreakfastItems)

                val filteredLunchItems = items.filter { it.userId == mainUser.id && it.type == "Lunch" }
                lunchAdapter.submitList(filteredLunchItems)

                val filteredDinnerItems = items.filter { it.userId == mainUser.id && it.type == "Dinner" }
                dinnerAdapter.submitList(filteredDinnerItems)

                val filteredSnackItems = items.filter { it.userId == mainUser.id && it.type == "Snack" }
                snackAdapter.submitList(filteredSnackItems)

                // Updates boxes
                val filteredItems = items.filter {it.userId == mainUser.id}
                var calories = 0;
                var protein = 0;
                var carbs = 0;
                var fat = 0;
                var fiber = 0;
                var water = 0;
                for (item in filteredItems)
                {
                    calories += item.itemCalories
                    protein += item.itemProtein
                    carbs += item.itemCarbs
                    fat += item.itemFat
                    fiber += item.itemFiber
                    water += item.itemWater
                }
                calTextView.text = calories.toString()
                proteinTextView.text = protein.toString()
                carbsTextView.text = carbs.toString()
                fatTextView.text = fat.toString()
                fiberTextView.text = fiber.toString()
                waterTextView.text = water.toString()

                // Update Goals
                var diffCal = mainUser.calorieGoal - calories
                var calText = ""
                // If number is POSITIVE, this means user has NOT met goal
                if (diffCal > 0)
                {
                    calText = "Calories: ${mainUser.calorieGoal} - ${calories} = ${diffCal} calories \n ${diffCal} calories remaining\n" + "\n"
                } else { // User has met the goal!
                    calText = "Calories: ${mainUser.calorieGoal} - ${calories} = ${diffCal} calories \n You have went over by ${diffCal*-1} calories\n" + "\n"
                }

                var diffWeight = mainUser.weightGoal - mainUser.currentWeight
                var weightText = ""
                // If the number is NEGATIVE, this means user has NOT met goal
                if (diffWeight < 0)
                {
                    weightText = "Weight: ${mainUser.weightGoal} - ${mainUser.currentWeight} = ${diffWeight} pounds \n ${diffWeight*-1} pounds to go!!!"
                } else {  // User has met the goal!
                    weightText = "Weight: ${mainUser.weightGoal} - ${mainUser.currentWeight} = ${diffWeight} pounds \n \"You have met your goal by ${diffWeight} pounds!! \\n Great job!!"
                }

                goalsTextView.text = "Goal Tracking: \n \n " + calText + weightText
            }
        }

        // Button variables
        val breakfastAdd = findViewById<FloatingActionButton>(R.id.breakfastBoxAdd)
        val lunchAdd = findViewById<FloatingActionButton>(R.id.lunchBoxAdd)
        val dinnerAdd = findViewById<FloatingActionButton>(R.id.dinnerBoxAdd)
        val snackAdd = findViewById<FloatingActionButton>(R.id.snackBoxAdd)
        val userSetting = findViewById<Button>(R.id.userSettings)

        // OnClick Listeners
        userSetting.setOnClickListener{
            val settingsIntent = Intent(this@MainActivity, SettingsActivity::class.java)
            settingsIntent.putExtra("UserID", userId)
            startActivity(settingsIntent)
        }
        breakfastAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("type", "Breakfast")
            itemIntent.putExtra("UserID", userId)
            startActivity(itemIntent)
        }

        lunchAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("type", "Lunch")
            itemIntent.putExtra("UserID", userId)
            startActivity(itemIntent)
        }

        dinnerAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("type", "Dinner")
            itemIntent.putExtra("UserID", userId)
            startActivity(itemIntent)
        }
        snackAdd.setOnClickListener{
            val itemIntent = Intent(this@MainActivity,NewItem::class.java)
            itemIntent.putExtra("type", "Snack")
            itemIntent.putExtra("UserID", userId)
            startActivity(itemIntent)
        }
    }

}