package com.example.projectct.InterfaceAPI

object Common {
    private val BASE_URL = "https://aabfe0f94852.ngrok.io/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}