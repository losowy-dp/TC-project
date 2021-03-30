package com.example.projectct.helpClass

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
    private var car_owner: Int


    constructor(descriptions: String, price: String, currency: String, car_owner: Int, start_location: String, delivery_location: String) {
        this.descriptions = descriptions
        this.price = price
        this.currency = currency
        this.car_owner = car_owner
        this.start_location = start_location
        this.delivery_location = delivery_location
    }
}