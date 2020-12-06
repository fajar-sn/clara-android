package com.pens.it.d4b2018.clara_android.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest(
        @Expose
        @SerializedName("email")
        var email: String,

        @SerializedName("password")
        var password: String
)