package tech.fulbek_dev.clara_app.view.main

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import tech.fulbek_dev.clara_app.R
import tech.fulbek_dev.clara_app.view.utils.visible

class MainActivity : AppCompatActivity() {

    private lateinit var contentFrameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        setDashboardFragment()

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_bar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home_page_item_menu -> { setDashboardFragment() }
            R.id.reservation_page_item_menu -> { setReservationsFragment() }
            R.id.asset_page_item_menu -> { setAssetsFragment() }
            R.id.profile_page_item_menu -> { setProfileFragment() }
        }

        true
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame_layout, fragment)
            commit()
        }
    }
    
    private fun initializeView() {
        setContentView(R.layout.activity_main)
        contentFrameLayout = findViewById(R.id.content_frame_layout)
        contentFrameLayout.visible(true)
    }

    private fun setDashboardFragment() {
        val dashboardFragment = DashboardFragment.newInstance()
        setCurrentFragment(dashboardFragment)
    }

    private fun setAssetsFragment() {
        val assetsFragment = AssetsFragment.newInstance()
        setCurrentFragment(assetsFragment)
    }

    private fun setReservationsFragment() {
        val reservationsFragment = ReservationsFragment.newInstance()
        setCurrentFragment(reservationsFragment)
    }

    private fun setProfileFragment() {
        val profileFragment = ProfileFragment.newInstance()
        setCurrentFragment(profileFragment)
    }
}