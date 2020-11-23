package com.pens.it.d4b2018.clara_android.data.models;

public class BaseModel {

    private String statusMsg;

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
