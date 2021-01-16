package tech.fulbek_dev.clara_app.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class History (
        @Expose
        @SerializedName("status")
        val status : String,

        @Expose
        @SerializedName("datetime")
        val datetime : String,

//        val _id : Any // FIX LATER, whether fix it into _id class with oid or fix the backend to return string of id
) {
        @Expose
        @SerializedName("_id")
        lateinit var id: Any

        constructor(
                id: Any,
                status : String,
                datetime : String
        ): this(status, datetime) {
                this.id = id
        }
}