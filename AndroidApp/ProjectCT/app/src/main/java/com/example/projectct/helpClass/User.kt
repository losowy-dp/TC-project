package com.example.projectct.helpClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//TO Django Database
class User {
    @SerializedName("email")
    @Expose
    private var email: String
    @SerializedName("username")
    @Expose
    private var username: String
    @SerializedName("password")
    @Expose
    private var password: String


    constructor(password: String, username: String, email: String) {
        this.password = password
        this.username = username
        this.email = email
    }
}