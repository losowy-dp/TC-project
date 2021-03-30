package com.example.projectct.InterfaceAPI

object Common {
    private val BASE_URL = "https://b874cea8cf60.ngrok.io/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}