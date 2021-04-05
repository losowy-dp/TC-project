package com.example.projectct.InterfaceAPI

object Common {
    private val BASE_URL = "https://c72c4c06c2ff.ngrok.io/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}