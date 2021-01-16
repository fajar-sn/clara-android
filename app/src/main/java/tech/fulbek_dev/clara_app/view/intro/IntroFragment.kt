package tech.fulbek_dev.clara_app.view.intro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import tech.fulbek_dev.clara_app.R
import tech.fulbek_dev.clara_app.databinding.FragmentIntroBinding
import tech.fulbek_dev.clara_app.view.authentication.AuthActivity
import tech.fulbek_dev.clara_app.view.utils.startNewActivity

class IntroFragment() : Fragment(), View.OnClickListener {

    private lateinit var introFragmentBinding: FragmentIntroBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        introFragmentBinding = FragmentIntroBinding.inflate(layoutInflater)

        introFragmentBinding.introLayout.setOnClickListener(this)
        introFragmentBinding.introImageView.setOnClickListener(this)
        introFragmentBinding.aboutUsTextView.setOnClickListener(this)

        return introFragmentBinding.root
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.intro_layout, R.id.intro_image_view -> {
                requireActivity().startNewActivity(AuthActivity::class.java)
            }
            R.id.about_us_text_view -> {
                NavHostFragment.findNavController(this).navigate(R.id.action_introFragment_to_aboutUsFragment)
            }
        }
    }

}