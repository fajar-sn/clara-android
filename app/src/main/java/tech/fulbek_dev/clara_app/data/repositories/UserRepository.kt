package tech.fulbek_dev.clara_app.data.repositories

import tech.fulbek_dev.clara_app.data.remote.APIService

class UserRepository(
        override val api: APIService
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}