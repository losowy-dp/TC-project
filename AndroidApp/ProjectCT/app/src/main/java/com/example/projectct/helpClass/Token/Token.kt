package com.example.projectct.helpClass.Token

import com.google.gson.annotations.SerializedName

data class Token(
        @SerializedName("refresh")
        var refreshToken: String?=null,
        @SerializedName("access")
        var authToken: String?=null,
        @SerializedName("detail")
        var detail: String?=null

)
