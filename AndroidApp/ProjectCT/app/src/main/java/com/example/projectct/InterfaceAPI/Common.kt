package com.example.projectct.InterfaceAPI

object Common {
    private val BASE_URL = "https://1b0c1c34a9ce.ngrok.io/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}