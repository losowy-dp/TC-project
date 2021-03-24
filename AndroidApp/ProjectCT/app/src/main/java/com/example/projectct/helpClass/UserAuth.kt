package com.example.projectct.helpClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserAuth {
    @SerializedName("username")
    @Expose
    private var username: String
    @SerializedName("password")
    @Expose
    private var password: String

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }
}