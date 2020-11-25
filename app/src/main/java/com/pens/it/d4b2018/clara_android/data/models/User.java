package com.pens.it.d4b2018.clara_android.data.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseModel {

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nrp")
    @Expose
    private String nrp;

    @SerializedName("class")
    @Expose
    private String _class;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("password")
    private String password;

    public User(String fullName,
                String email,
                String nrp,
                String _class,
                String role,
                String updatedAt,
                String createdAt,
                String password) {
        this.fullName = fullName;
        this.email = email;
        this.nrp = nrp;
        this._class = _class;
        this.role = role;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "User {" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", nrp='" + nrp + '\'' +
                ", class='" + _class + '\'' +
                ", role='" + role + '\'' +
                ", id='" + id + '\'' +
                '}';

    }
}
