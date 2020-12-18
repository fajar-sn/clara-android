package com.pens.it.d4b2018.clara_android.mvvm.view.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginRequest
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginResponse
import com.pens.it.d4b2018.clara_android.mvvm.data.models.User
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.Resource
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AuthRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class AuthViewModel(
        private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    private val _registerResponse : MutableLiveData<Resource<ResponseBody>> = MutableLiveData()

    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    val registerResponse: LiveData<Resource<ResponseBody>>
        get() = _registerResponse

    fun login(request: LoginRequest) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(request)
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }

    fun register(user: User) = viewModelScope.launch {
        _registerResponse.value = Resource.Loading
        _registerResponse.value = repository.performRegister(user)
    }

}