package com.example.projectct.helpClass.Transport

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateTransportations {
    @SerializedName("start_location")
    @Expose
    private var start_location: String
    @SerializedName("description")
    @Expose
    private var descriptions: String
    @SerializedName("delivery_location")
    @Expose
    private var delivery_location: String
    @SerializedName("price")
    @Expose
    private var price: String
    @SerializedName("currency")
    @Expose
    private var currency: String
    @SerializedName("car_owner")
    @Expose
    private var car_owner: String
    @SerializedName("punktA_1")
    @Expose
    private var punktA_1: String
    @SerializedName("punktA_2")
    @Expose
    private var punktA_2: String
    @SerializedName("punktB_1")
    @Expose
    private var punktB_1: String
    @SerializedName("punktB_2")
    @Expose
    private var punktB_2: String


    constructor(descriptions: String, price: String, currency: String, car_owner: String, start_location: String, delivery_location: String,punktA_1: String,punktA_2: String,punktB_1: String,punktB_2: String) {
        this.descriptions = descriptions
        this.price = price
        this.currency = currency
        this.car_owner = car_owner
        this.start_location = start_location
        this.delivery_location = delivery_location
        this.punktA_1 = punktA_1
        this.punktA_2 = punktA_2
        this.punktB_1 = punktB_1
        this.punktB_2 = punktB_2
    }
}