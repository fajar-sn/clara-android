package com.pens.it.d4b2018.clara_android.mvvm.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pens.it.d4b2018.clara_android.databinding.FragmentDashboardBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.UserRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DashboardFragment : BaseFragment<UserViewModel, FragmentDashboardBinding, UserRepository>() {

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return UserRepository(api)
    }

    companion object {
        fun newInstance(): DashboardFragment = DashboardFragment()
    }

}