package com.example.projectct.InterfaceAPI

import com.example.projectct.helpClass.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @POST("/auth/jwt/create/")
    fun login(@Body data: UserAuth): Call<Token>

    @POST("/auth/users/")
    fun register(@Body data: User): Call<Token>

    @GET("/transportations/getTransportions/")
    fun getAll(): Call<List<TransportationPrimary>>

    @POST("/transportations/getTransportions/")
    fun createTransport(@Body data: CreateTransportations): Call<TransportationPrimary>
}