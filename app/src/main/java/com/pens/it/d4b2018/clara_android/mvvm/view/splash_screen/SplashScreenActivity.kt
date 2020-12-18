package com.pens.it.d4b2018.clara_android.mvvm.view.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pens.it.d4b2018.clara_android.R
import com.pens.it.d4b2018.clara_android.mvvm.view.authentication.AuthActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val thisActivity = this
        Timer("SettingUp", false).schedule(1000) {
            val intent = Intent(thisActivity, AuthActivity::class.java)
            startActivity(intent)
            thisActivity.finish()
        }
    }

}