package tech.fulbek_dev.clara_app.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import tech.fulbek_dev.clara_app.data.remote.RetrofitClient
import tech.fulbek_dev.clara_app.data.repositories.BaseRepository
import tech.fulbek_dev.clara_app.view.authentication.AuthActivity
import tech.fulbek_dev.clara_app.view.utils.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import tech.fulbek_dev.clara_app.data.local.UserPreferences

abstract class BaseFragment<VM: BaseViewModel, B: ViewBinding, R: BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var viewModel : VM
    protected val retrofitClient = RetrofitClient
    protected lateinit var binding: B

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch { userPreferences.authToken.first() }

        return binding.root
    }

    fun logout() = lifecycleScope.launch {
        val authToken = userPreferences.authToken.first()
        val api = retrofitClient.buildApi(authToken)
        viewModel.logout(api)
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R

}