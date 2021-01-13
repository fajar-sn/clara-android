package tech.fulbek_dev.clara_app.data.remote

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import tech.fulbek_dev.clara_app.data.models.*

interface APIService {
    @POST(APIUtils.LOGIN_URL)
    suspend fun performLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST(APIUtils.REGISTER_URL)
    suspend fun performRegister(@Body user: User): ResponseBody

    @GET(APIUtils.PROFILE_URL)
    suspend fun getUser(): UserResponse

    @POST(APIUtils.LOGOUT_URL)
    suspend fun logout(): ResponseBody

    @GET(APIUtils.ASSETS_URL)
    suspend fun getAssets(
            @Query("search") search: String?,
            @Query("page") page: Int
    ): AssetsResponse

    @GET(APIUtils.RESERVATIONS_URL)
    suspend fun getReservations(
            @Query("search") search: String?,
            @Query("page") page: Int
    ): ReservationsResponse

}