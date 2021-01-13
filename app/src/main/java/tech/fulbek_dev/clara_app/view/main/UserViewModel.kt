package tech.fulbek_dev.clara_app.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.fulbek_dev.clara_app.data.models.UserResponse
import tech.fulbek_dev.clara_app.data.remote.Resource
import tech.fulbek_dev.clara_app.data.repositories.UserRepository
import tech.fulbek_dev.clara_app.view.base.BaseViewModel

class UserViewModel(
        private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val user: LiveData<Resource<UserResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}