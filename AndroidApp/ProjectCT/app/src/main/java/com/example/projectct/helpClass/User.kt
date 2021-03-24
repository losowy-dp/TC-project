package com.example.projectct.helpClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//TO Django Database
class User {
    @SerializedName("username")
    @Expose
    private var username: String
    @SerializedName("password")
    @Expose
    private var password: String
    @SerializedName("full_name")
    @Expose
    private var full_name: String
    @SerializedName("phone")
    @Expose
    private var phone: String
    @SerializedName("email")
    @Expose
    private var email: String
    constructor(username: String, password: String) {
        this.full_name = "default"
        this.phone = "default"
        this.email = "default"
        this.username = username
        this.password = password
    }

    constructor(username: String, password: String, full_name: String, phone: String, email: String) {
        this.username = username
        this.password = password
        this.full_name = full_name
        this.phone = phone
        this.email = email
    }


}