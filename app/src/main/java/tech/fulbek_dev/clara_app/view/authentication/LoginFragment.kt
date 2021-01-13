package tech.fulbek_dev.clara_app.view.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.launch
import tech.fulbek_dev.clara_app.R
import tech.fulbek_dev.clara_app.data.models.LoginRequest
import tech.fulbek_dev.clara_app.data.remote.Resource
import tech.fulbek_dev.clara_app.data.repositories.AuthRepository
import tech.fulbek_dev.clara_app.databinding.FragmentLoginBinding
import tech.fulbek_dev.clara_app.view.base.BaseFragment
import tech.fulbek_dev.clara_app.view.main.MainActivity
import tech.fulbek_dev.clara_app.view.utils.enable
import tech.fulbek_dev.clara_app.view.utils.handleApiError
import tech.fulbek_dev.clara_app.view.utils.startNewActivity
import tech.fulbek_dev.clara_app.view.utils.visible

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.visible(false)
        binding.signinBtn.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressBar.visible(it is Resource.Loading)

            if (it is Resource.Success) {
                lifecycleScope.launch {
                    viewModel.saveAuthToken(it.value.token)
                    requireActivity().startNewActivity(MainActivity::class.java)
                }
            }
            else if (it is Resource.Failure) handleApiError(it) { login() }
        })

        binding.emailInput.addTextChangedListener {
            val email = it.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            binding.signinBtn.enable(email.isNotEmpty() && password.isNotEmpty())
        }

        binding.passwordInput.addTextChangedListener {
            val email = binding.emailInput.text.toString().trim()
            val password = it.toString().trim()
            binding.signinBtn.enable(email.isNotEmpty() && password.isNotEmpty())
        }

        setButtonOnClick()

    }

    private fun login() {
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val request = LoginRequest(email, password)
        viewModel.login(request)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(retrofitClient.buildApi(), userPreferences)

    private fun setButtonOnClick() {
        val thisFragment = this

        binding.signinBtn.setOnClickListener {
            login()
        }

        binding.buttonSignupPage.setOnClickListener {
            NavHostFragment.findNavController(thisFragment).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}