package com.pens.it.d4b2018.clara_android.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class History (
        @Expose
        @SerializedName("status")
        val status : String,

        @Expose
        @SerializedName("datetime")
        val datetime : String,

        @Expose
        @SerializedName("_id")
        val _id : Object // FIX LATER, whether fix it into _id class with oid or fix the backend to return string of id
)