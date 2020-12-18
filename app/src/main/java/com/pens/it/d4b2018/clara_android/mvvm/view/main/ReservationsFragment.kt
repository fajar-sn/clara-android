package com.pens.it.d4b2018.clara_android.mvvm.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pens.it.d4b2018.clara_android.databinding.ListFragmentBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.UserRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ReservationsFragment : BaseFragment<MainViewModel, ListFragmentBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = "Reservations"
        binding.titleTextView.text = title
    }

    override fun getViewModel() = MainViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = ListFragmentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(APIService::class.java, token)
        return UserRepository(api)
    }

    companion object {
        fun newInstance(): ReservationsFragment = ReservationsFragment()
    }

}