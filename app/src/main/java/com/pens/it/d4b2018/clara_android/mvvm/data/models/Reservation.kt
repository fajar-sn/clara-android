package com.pens.it.d4b2018.clara_android.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Reservation (
        @Expose
        @SerializedName("_id")
        val id : String,

        @Expose
        @SerializedName("status")
        val status : String,

        @Expose
        @SerializedName("description")
        val description : String,

        @Expose
        @SerializedName("begin")
        val begin : String,

        @Expose
        @SerializedName("end")
        val end : String,

        @Expose
        @SerializedName("user")
        val user : User,

        @Expose
        @SerializedName("asset")
        val asset : Asset,

        @Expose
        @SerializedName("history")
        val history : List<History>
)