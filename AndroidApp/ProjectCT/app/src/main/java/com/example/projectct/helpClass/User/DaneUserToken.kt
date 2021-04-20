package com.example.projectct.helpClass.User

data class DaneUserToken(
    var id: String,
    var email: String?=null,
    var username: String?=null,
    var detail: String?=null
)
data class NewUser(
        var email: String?=null,
        var username: String?=null,
        var id: String?=null
)

data class Costyl(
        var phone: String?=null,
        var first_name: String?=null,
        var last_name: String?=null,
        var email: String?=null
)