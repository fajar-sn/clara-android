package com.pens.it.d4b2018.clara_android.backup.data.models;

import com.google.gson.annotations.SerializedName;

public class BaseModel {

    @SerializedName("message")
    private String statusMsg;

    @SerializedName("code")
    private String statusCode;

    public String getStatusMsg() {
        return statusMsg.replace("error:", "");
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}
