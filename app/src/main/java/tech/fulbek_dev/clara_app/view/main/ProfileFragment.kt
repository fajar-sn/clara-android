package tech.fulbek_dev.clara_app.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.flow.first
import tech.fulbek_dev.clara_app.databinding.FragmentProfileBinding
import tech.fulbek_dev.clara_app.data.models.UserResponse
import tech.fulbek_dev.clara_app.data.remote.Resource
import tech.fulbek_dev.clara_app.data.repositories.UserRepository
import tech.fulbek_dev.clara_app.view.base.BaseFragment
import tech.fulbek_dev.clara_app.view.utils.visible
import kotlinx.coroutines.runBlocking

class ProfileFragment: BaseFragment<UserViewModel, FragmentProfileBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false)
                    updateUI(it.value)
                }

                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                }
            }
        })

        binding.profileLogoutButton.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(userResponse: UserResponse) {
        val user = userResponse.user
        with(binding) {
            profileUserNameTextview.text = user.fullName
            profileUserClassTextview.text = user.grade
            profileUserNrpTextview.text = user.nrp
        }
    }

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return UserRepository(api)
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

}