package tech.fulbek_dev.clara_app.view.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import tech.fulbek_dev.clara_app.data.models.LoginRequest
import tech.fulbek_dev.clara_app.data.models.LoginResponse
import tech.fulbek_dev.clara_app.data.models.User
import tech.fulbek_dev.clara_app.data.remote.Resource
import tech.fulbek_dev.clara_app.data.repositories.AuthRepository
import tech.fulbek_dev.clara_app.view.base.BaseViewModel

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