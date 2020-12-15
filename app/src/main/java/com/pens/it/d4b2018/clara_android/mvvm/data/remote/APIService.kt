package com.pens.it.d4b2018.clara_android.mvvm.data.remote

import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginRequest
import com.pens.it.d4b2018.clara_android.mvvm.data.models.LoginResponse
import com.pens.it.d4b2018.clara_android.mvvm.data.models.User
import com.pens.it.d4b2018.clara_android.mvvm.data.models.UserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @POST(APIUtils.LOGIN_URL)
    suspend fun performLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST(APIUtils.REGISTER_URL)
    suspend fun performRegister(@Body user: User): ResponseBody

    @GET(APIUtils.PROFILE_URL)
    suspend fun getUser(): UserResponse

    @POST(APIUtils.LOGOUT_URL)
    suspend fun logout(): ResponseBody
}