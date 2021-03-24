package com.example.projectct.helpClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token(
        var detail: String?=null,
        var refresh: String?=null,
        var access: String?=null

)
