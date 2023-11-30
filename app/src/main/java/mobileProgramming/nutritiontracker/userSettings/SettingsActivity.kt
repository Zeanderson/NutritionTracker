package mobileProgramming.nutritiontracker.userSettings

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.UserApplication

class SettingsActivity : AppCompatActivity() {

    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory((application as UserApplication).repository, -1)
    }
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.user_settings)

        // -------------------- \\
        //  Reference Calls       \\
        // ------------------------ \\

        // Button variables

        // OnClick Listeners
    }


}