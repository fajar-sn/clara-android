package tech.fulbek_dev.clara_app.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
        @Expose
        @SerializedName("full_name")
        var fullName: String,

        @Expose
        @SerializedName("email")
        var email: String,

        @Expose
        @SerializedName("nrp")
        var nrp: String,

        @Expose
        @SerializedName("grade")
        var grade: String,

        @Expose
        @SerializedName("role")
        var role: String = "Student",

        @Expose
        @SerializedName("updated_at")
        var updatedAt: String? = null,

        @Expose
        @SerializedName("created_at")
        var createdAt: String? = null,

        @SerializedName("_id")
        @Expose
        var id: String? = null,

        @SerializedName("password")
        var password: String? = null

)