package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.pens.it.d4b2018.clara_android.R
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val thisFragment = this
        Timer("SettingUp", false).schedule(1000) {
            NavHostFragment.findNavController(thisFragment).navigate(R.id.action_splashScreenFragment_to_loginFragment)

        }
    }

}