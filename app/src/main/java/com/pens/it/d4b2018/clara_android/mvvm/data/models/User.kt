package com.pens.it.d4b2018.clara_android.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(
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
        @SerializedName("class")
        var class_: String,

        @Expose
        @SerializedName("role")
        var role: String,

        @Expose
        @SerializedName("updated_at")
        var updatedAt: String,

        @Expose
        @SerializedName("created_at")
        var createdAt: String,

        @SerializedName("_id")
        @Expose
        var id: String,

        @SerializedName("password")
        var password: String?


)