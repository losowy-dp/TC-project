package com.example.projectct.InterfaceAPI

import com.example.projectct.helpClass.Token.Token
import com.example.projectct.helpClass.Transport.CreateTransportations
import com.example.projectct.helpClass.Transport.TransportationPrimary
import com.example.projectct.helpClass.User.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    //POST Request
    //LoginActivity and RegisterActivity
    @POST("/auth/jwt/create/")
    fun login(@Body data: UserAuth): Call<Token>
    //RegisterActivity
    @POST("/auth/users/")
    fun register(@Body data: User): Call<DaneUserToken>
    //Add_order
    @POST("/transportations/getTransportions/")
    fun createTransport(@Body data: CreateTransportations): Call<TransportationPrimary>
    @POST("/auth/users/set_password/")
    fun resetPassword(@Header("Authorization")token: String,@Body data: Password): Call<Int>
    //GET Request
    //All_order_list
    @GET("/transportations/getTransportions/")
    fun getAll(): Call<List<TransportationPrimary>>
    //All_order_list sorted New
    @GET("/transportations/getTransportionsSortNew")
    fun getAllSortNew(): Call<List<TransportationPrimary>>
    //All_order_list sorted Old
    @GET("/transportations/getTransportionsSortOld")
    fun getAllSortOld(): Call<List<TransportationPrimary>>
    //All_order_list sorted price Fall
    @GET("/transportations/getTransportionsPriceF")
    fun getAllSortPriceF(): Call<List<TransportationPrimary>>
    //All_order_list sorted price Incr
    @GET("/transportations/getTransportionsPriceI")
    fun getAllSortPriceI(): Call<List<TransportationPrimary>>
    //Order
    @GET("/transportations/transport/{id}")
    fun takeTransport(@Path("id")id: String): Call<TransportationPrimary>

    @GET("/auth/users/me")
    fun fetchDana(@Header("Authorization")token: String): Call<DaneUserToken>

    @GET("/account/userDetail/{id}")
    fun takeInfoUser(@Path("id")id: String): Call<UserInfo>

    @GET("/account/profileDetail/{id}")
    fun takeInfoProfile(@Path("id")id: String): Call<UserPhone>


    @GET("/transportations/ordered/{id}/")
    fun historyOrder(@Path("id")id: String): Call<List<TransportationPrimary>>
    @GET("/transportations/orderedSort/{id}/")
    fun historyOrderSort(@Path("id")id: String): Call<List<TransportationPrimary>>
    @GET("/transportations/orderedSortPriceF/{id}/")
    fun historyOrderSortPriceF(@Path("id")id: String): Call<List<TransportationPrimary>>
    @GET("/transportations/orderedSortPriceI/{id}/")
    fun historyOrderSortPriceI(@Path("id")id: String): Call<List<TransportationPrimary>>

    //PUT Request
    @PUT("/account/userDetail/{id}/")
    fun changeUserDetail(@Path("id")id: String,@Body user: UserInfo): Call<String>
    @PUT("/account/profileDetail/{id}/")
    fun changeProfileDetail(@Path("id")id: String,@Body user: UserPhone): Call<String>
}