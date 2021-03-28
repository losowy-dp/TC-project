package com.example.projectct.InterfaceAPI

import com.example.projectct.helpClass.Token
import com.example.projectct.helpClass.User
import com.example.projectct.helpClass.UserAuth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @POST("/auth/jwt/create/")
    fun login(@Body data: UserAuth): Call<Token>

    @POST("/auth/users/")
    fun register(@Body data: User): Call<Token>

}