package com.pens.it.d4b2018.clara_android.data.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse extends User {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;

    @SerializedName("role")
    @Expose
    private String role;

    public UserResponse(String fullName, String email, String nrp, String _class, String role, String updatedAt, String createdAt, String password) {
        super(fullName, email, nrp, _class, role, updatedAt, createdAt, password);
    }

    public UserResponse(String email, String password) {
        super(email, password);
    }

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

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
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
                ", expiresIn='" + expiresIn + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}