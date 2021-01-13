package tech.fulbek_dev.clara_app.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun buildApi(
            authToken: String? = null
    ): APIService {
        return Retrofit.Builder()
                .baseUrl(APIUtils.BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            chain.proceed(chain.request().newBuilder().also {
                                it.addHeader("Authorization", "Bearer $authToken")
                            }.build())
                        }.also { client ->
                        if (tech.fulbek_dev.clara_app.BuildConfig.DEBUG) {
                            val interceptor = HttpLoggingInterceptor()
                            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(interceptor)
                        }
                    }.build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
    }

}