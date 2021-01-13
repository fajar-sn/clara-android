package tech.fulbek_dev.clara_app.data.models

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