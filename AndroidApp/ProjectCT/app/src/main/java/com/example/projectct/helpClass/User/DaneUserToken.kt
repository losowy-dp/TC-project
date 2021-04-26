package com.example.projectct.helpClass.User

data class DaneUserToken(
    var id: String?=null,
    var email: String?=null,
    var username: String?=null,
    var detail: String?=null
)
data class UserInfo(
        var id: String?=null,
        var first_name: String?=null,
        var last_name: String?=null,
        var email: String?=null
)
data class UserPhone(
        var id: String?=null,
        var number_of_phone: String?=null
)
data class Password(
        var new_password: String?=null,
        var current_password: String?=null
)

