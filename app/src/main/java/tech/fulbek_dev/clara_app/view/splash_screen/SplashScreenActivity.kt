package tech.fulbek_dev.clara_app.view.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.fulbek_dev.clara_app.R
import tech.fulbek_dev.clara_app.databinding.ActivityMainBinding
import tech.fulbek_dev.clara_app.databinding.FragmentAboutUsBinding
import tech.fulbek_dev.clara_app.databinding.FragmentSplashScreenBinding
import tech.fulbek_dev.clara_app.view.authentication.AuthActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var splashScreenFragmentBinding: FragmentSplashScreenBinding
    private lateinit var aboutUsFragmentBinding: FragmentAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash_screen)
        val thisActivity = this
        Timer("SettingUp", false).schedule(1000) {
            val intent = Intent(thisActivity, AuthActivity::class.java)
            startActivity(intent)
            thisActivity.finish()
        }
    }

}