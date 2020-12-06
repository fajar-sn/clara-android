package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.pens.it.d4b2018.clara_android.R
import com.pens.it.d4b2018.clara_android.mvvm.data.local.UserPreferences

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            Toast.makeText(this, it ?: "Token is Null", Toast.LENGTH_SHORT).show()
        })
    }
}