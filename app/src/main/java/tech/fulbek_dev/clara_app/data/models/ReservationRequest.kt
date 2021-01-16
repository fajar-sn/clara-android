package tech.fulbek_dev.clara_app.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReservationRequest (
        @Expose
        @SerializedName("description")
        val description: String,

        @Expose
        @SerializedName("begin")
        val begin: String,

        @Expose
        @SerializedName("end")
        val end: String,

        @Expose
        @SerializedName("asset_id")
        val assetId: String,

        @Expose
        @SerializedName("quantity")
        val quantity: Int,
)