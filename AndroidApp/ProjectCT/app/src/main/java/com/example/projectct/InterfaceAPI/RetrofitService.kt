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
    fun register(@Body data: User): Call<NewUser>

    @GET("/transportations/getTransportions/")
    fun getAll(): Call<List<TransportationPrimary>>

    @POST("/transportations/getTransportions/")
    fun createTransport(@Body data: CreateTransportations): Call<TransportationPrimary>

    @GET("/transportations/{id}")
    fun takeTransport(@Path("id")id: String): Call<List<TransportationPrimary>>

    @GET("/auth/users/me")
    fun fetchDana(@Header("Authorization")token: String): Call<DaneUserToken>

    @GET("/account/profile/{id}")
    fun takeInfoPrimitive(@Path("id")id: String): Call<List<UserPhone>>

    @POST("/account/profile/edit/{id}")
    fun changeInfo(@Path("id")id: String,@Body dane: UserPhone): Call<String>

    @POST("/account/edit_profile/")
    fun changeProfileInfo(@Body dane: UserPhonePrim): Call<String>

    @POST("/account/create_profile/{id}")
    fun createProfile(@Path("id")id:String): Call<String>

    @GET("/transportations/ordered/{id}")
    fun historyOrder(@Path("id")id: String): Call<List<TransportationPrimary>>


    @POST("/edit_kastyl/{id}")
    fun kostyl(@Path("id")id: String,@Body dane: Costyl): Call<String>
}