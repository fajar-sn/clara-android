package tech.fulbek_dev.clara_app.view.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import tech.fulbek_dev.clara_app.R
import tech.fulbek_dev.clara_app.data.local.UserPreferences
import tech.fulbek_dev.clara_app.view.main.MainActivity

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, {
            if (it != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            } else
                setContentView(R.layout.activity_auth)
        })
    }
}