package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.pens.it.d4b2018.clara_android.R
import com.pens.it.d4b2018.clara_android.mvvm.data.local.UserPreferences
import com.pens.it.d4b2018.clara_android.mvvm.view.main.MainActivity

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            if (it != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            } else
                setContentView(R.layout.activity_auth)
        })
    }
}