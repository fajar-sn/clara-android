package com.pens.it.d4b2018.clara_android.mvvm.data.remote

import com.pens.it.d4b2018.clara_android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun <Api> buildApi(
            api: Class<Api>,
            authToken: String? = null
    ): Api {
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
                .create(api)
    }

}