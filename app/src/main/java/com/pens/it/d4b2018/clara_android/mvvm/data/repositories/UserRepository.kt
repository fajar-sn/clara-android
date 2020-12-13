package com.pens.it.d4b2018.clara_android.mvvm.data.repositories

import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService

class UserRepository(
        override val api: APIService
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}