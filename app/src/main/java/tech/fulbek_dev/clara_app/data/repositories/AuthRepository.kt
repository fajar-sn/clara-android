package tech.fulbek_dev.clara_app.data.repositories

import tech.fulbek_dev.clara_app.data.local.UserPreferences
import tech.fulbek_dev.clara_app.data.models.LoginRequest
import tech.fulbek_dev.clara_app.data.models.User
import tech.fulbek_dev.clara_app.data.remote.APIService

class AuthRepository(
        override val api : APIService,
        private val preferences: UserPreferences
) : BaseRepository(api) {

    suspend fun login(loginRequest: LoginRequest) = safeApiCall {
        api.performLogin(loginRequest)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

    suspend fun performRegister(user: User) = safeApiCall {
        api.performRegister(user)
    }

}