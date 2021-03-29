package com.example.projectct.InterfaceAPI

object Common {
    private val BASE_URL = "https://kostia.pagekite.me/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}