package com.example.projectct.InterfaceAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService: RetrofitService

    fun getApiService(): RetrofitService{
        if(!::apiService.isInitialized){
            val retrofit  = Retrofit.Builder().baseUrl("http://testcartransport.ddns.net:8000")
                .addConverterFactory(GsonConverterFactory.create()).build()
            apiService = retrofit.create(RetrofitService::class.java)
        }
        return apiService
    }
}