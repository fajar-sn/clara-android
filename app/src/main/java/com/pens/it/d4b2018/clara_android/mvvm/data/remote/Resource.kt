package com.pens.it.d4b2018.clara_android.mvvm.data.remote

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
            val isNetworkError: Boolean,
            val errorCode: Int?,
            val errorBody: ResponseBody?
    ) : Resource<Nothing>()
    object Loading: Resource<Nothing>()
}
