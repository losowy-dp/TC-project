package com.example.projectct.helpClass.User

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
    @SerializedName("first_name")
    @Expose
    private var first_name: String
    @SerializedName("last_name")
    @Expose
    private var last_name: String

    constructor(password: String, username: String, email: String,first_name: String, last_name: String) {
        this.password = password
        this.username = username
        this.email = email
        this.first_name = first_name
        this.last_name = last_name
    }
}