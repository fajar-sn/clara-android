package tech.fulbek_dev.clara_app.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
        @Expose
        @SerializedName("user")
        var user: User,
)
