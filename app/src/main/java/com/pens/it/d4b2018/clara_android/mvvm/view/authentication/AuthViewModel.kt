package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginRequest
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginResponse
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.Resource
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
        private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(request: LoginRequest) = viewModelScope.launch {
        _loginResponse.value = repository.login(request)
    }

}