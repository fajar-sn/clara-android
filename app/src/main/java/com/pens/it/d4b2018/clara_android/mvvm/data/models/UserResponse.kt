package com.pens.it.d4b2018.clara_android.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
        @Expose
        @SerializedName("user")
        var userrr: User,
)
