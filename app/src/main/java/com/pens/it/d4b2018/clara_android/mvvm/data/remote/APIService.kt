package com.pens.it.d4b2018.clara_android.mvvm.data.remote

import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginRequest
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginResponse
import com.pens.it.d4b2018.clara_android.mvvm.data.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST(APIUtils.LOGIN_URL)
    suspend fun performLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST(APIUtils.REGISTER_URL)
    fun performRegister(@Body user: User?): Call<User?>?
}