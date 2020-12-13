package com.pens.it.d4b2018.clara_android.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @Expose
        @SerializedName("token")
        var token: String,

        @Expose
        @SerializedName("token_type")
        var tokenType: String,

        @Expose
        @SerializedName("role")
        var role: String
)