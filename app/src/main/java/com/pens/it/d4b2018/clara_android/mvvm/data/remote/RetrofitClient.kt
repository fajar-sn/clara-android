package com.pens.it.d4b2018.clara_android.mvvm.data.remote

import com.pens.it.d4b2018.clara_android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun <Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
                .baseUrl(APIUtils.BASE_URL)
                .client(
                    OkHttpClient.Builder().also { client ->
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