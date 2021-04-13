package com.example.projectct.InterfaceAPI

import com.example.projectct.helpClass.Token.Token
import com.example.projectct.helpClass.Transport.CreateTransportations
import com.example.projectct.helpClass.Transport.TransportationPrimary
import com.example.projectct.helpClass.User.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @POST("/auth/jwt/create/")
    fun login(@Body data: UserAuth): Call<Token>

    @POST("/auth/users/")
    fun register(@Body data: User): Call<Token>

    @GET("/transportations/getTransportions/")
    fun getAll(): Call<List<TransportationPrimary>>

    @POST("/transportations/getTransportions/")
    fun createTransport(@Body data: CreateTransportations): Call<TransportationPrimary>

    @GET("/transportations/{id}")
    fun takeTransport(@Path("id")id: String): Call<List<TransportationPrimary>>

    @GET("/auth/users/me")
    fun fetchDana(@Header("Authorization")token: String): Call<DaneUserToken>

    @GET("/accounts/profile/{id}")
    fun takeInfoPrimitive(@Path("id")id: String): Call<List<UserPhone>>

    @POST("/accounts/profile/edit/{id}")
    fun changeInfo(@Path("id")id: String,@Body dane: UserPhone): Call<String>
}