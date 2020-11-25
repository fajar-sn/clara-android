package com.pens.it.d4b2018.clara_android.data.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseModel {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("role")
    @Expose
    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NonNull
    @Override
    public String toString() {
        return "User Response {" +
                "token='" + token + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public LoginResponse(String token, String tokenType, String role) {
        this.token = token;
        this.tokenType = tokenType;
        this.role = role;
    }
}