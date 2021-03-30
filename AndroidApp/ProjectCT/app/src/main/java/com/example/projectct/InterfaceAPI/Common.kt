package com.example.projectct.InterfaceAPI

object Common {
    private val BASE_URL = "https://85fef420574f.ngrok.io"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}