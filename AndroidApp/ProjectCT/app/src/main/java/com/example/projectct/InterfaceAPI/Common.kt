package com.example.projectct.InterfaceAPI

object Common {
    private val BASE_URL = "http://10.206.3.111:8000"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}