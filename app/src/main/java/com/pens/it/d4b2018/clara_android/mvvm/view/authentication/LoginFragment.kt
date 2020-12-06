package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.pens.it.d4b2018.clara_android.databinding.FragmentLoginBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginRequest
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.Resource
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AuthRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        userPreferences.saveAuthToken(it.value.token)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.signinBtn.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val request = LoginRequest(email, password)
            // @todo add input validations
            viewModel.login(request)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(retrofitClient.buildApi(APIService::class.java))

}