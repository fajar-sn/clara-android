package com.pens.it.d4b2018.clara_android.mvvm.data.remote

import com.pens.it.d4b2018.clara_android.BuildConfig
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
                        if (BuildConfig.DEBUG) {
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