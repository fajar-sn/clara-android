package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.pens.it.d4b2018.clara_android.R
import com.pens.it.d4b2018.clara_android.databinding.FragmentRegisterBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.models.User
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.Resource
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AuthRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.enable
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.handleApiError
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.snackbar
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.visible
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.visible(false)
        binding.signupBtn.enable(false)

        val thisFragment = this

        viewModel.registerResponse.observe(viewLifecycleOwner, {
            binding.progressBar.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        requireView().snackbar("New user successfully registered")
                        NavHostFragment.findNavController(thisFragment).navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                }

                is Resource.Failure -> handleApiError(it) { registerNewUser() }
            }

        })

        setAddTextChangedListener()
        setButtonOnClick()

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(
                                        inflater,
                                        container,
                                        false
                                )

    override fun getFragmentRepository() = AuthRepository(
                                                    retrofitClient.buildApi(APIService::class.java),
                                                    userPreferences
                                            )

    private fun setButtonOnClick() {
        val thisFragment = this

        binding.signupBtn.setOnClickListener {
            if (!validatePassword())
                requireView().snackbar("Your password and confirmation password does not match")
            else
                registerNewUser()
        }

        binding.buttonSignin.setOnClickListener {
            NavHostFragment.findNavController(thisFragment).navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun registerNewUser() {
        val fullName = binding.fullNameEditText.text.toString().trim()
        val nrp = binding.nrpEditText.text.toString().trim()
        val grade = binding.gradeEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        val user = User(fullName, email, nrp, grade, password = password)
        viewModel.register(user)
    }

    private fun validatePassword() = binding.passwordEditText.text.toString().trim() == binding.confirmPasswordEditText.text.toString().trim()

    private fun setAddTextChangedListener() {

        var fullname = binding.fullNameEditText.text.toString().trim()
        var nrp = binding.nrpEditText.text.toString().trim()
        var grade = binding.gradeEditText.text.toString().trim()
        var email = binding.emailEditText.text.toString().trim()
        var password = binding.passwordEditText.text.toString().trim()
        var confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

        binding.fullNameEditText.addTextChangedListener {
            fullname = it.toString().trim()
            binding.signupBtn.enable(fullname.isNotEmpty() && nrp.isNotEmpty() && grade.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
        }

        binding.nrpEditText.addTextChangedListener {
            nrp = it.toString().trim()
            binding.signupBtn.enable(fullname.isNotEmpty() && nrp.isNotEmpty() && grade.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
        }

        binding.gradeEditText.addTextChangedListener {
            grade = it.toString().trim()
            binding.signupBtn.enable(fullname.isNotEmpty() && nrp.isNotEmpty() && grade.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
        }

        binding.emailEditText.addTextChangedListener {
            email = it.toString().trim()
            binding.signupBtn.enable(fullname.isNotEmpty() && nrp.isNotEmpty() && grade.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
        }

        binding.passwordEditText.addTextChangedListener {
            password = it.toString().trim()
            binding.signupBtn.enable(fullname.isNotEmpty() && nrp.isNotEmpty() && grade.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
        }

        binding.confirmPasswordEditText.addTextChangedListener {
            confirmPassword = it.toString().trim()
            binding.signupBtn.enable(fullname.isNotEmpty() && nrp.isNotEmpty() && grade.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
        }

    }

}