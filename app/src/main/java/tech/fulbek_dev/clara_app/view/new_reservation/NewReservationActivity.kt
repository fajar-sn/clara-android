package tech.fulbek_dev.clara_app.view.new_reservation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.fulbek_dev.clara_app.R
import tech.fulbek_dev.clara_app.databinding.ActivityMainBinding
import tech.fulbek_dev.clara_app.view.utils.visible

class NewReservationActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        mainActivityBinding.contentFrameLayout.visible(true)
        mainActivityBinding.bottomNavigationBar.visible(false)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame_layout, NewReservationFragment())
            commit()
        }
    }
}