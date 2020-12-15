package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pens.it.d4b2018.clara_android.R
import com.pens.it.d4b2018.clara_android.databinding.FragmentRegisterBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AuthRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(retrofitClient.buildApi(APIService::class.java), userPreferences)

}