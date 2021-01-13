package tech.fulbek_dev.clara_app.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @Expose
        @SerializedName("email")
        var email: String,

        @SerializedName("password")
        var password: String
)