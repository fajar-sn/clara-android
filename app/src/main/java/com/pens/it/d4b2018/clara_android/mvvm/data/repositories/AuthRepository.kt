package com.pens.it.d4b2018.clara_android.mvvm.data.repositories

import com.pens.it.d4b2018.clara_android.mvvm.data.local.UserPreferences
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginRequest
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService

class AuthRepository(
        private val api : APIService,
        private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(loginRequest: LoginRequest) = safeApiCall {
        api.performLogin(loginRequest)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

}