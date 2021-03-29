package com.example.projectct.helpClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransportationsPrimary {
    @SerializedName("start_location")
    @Expose
    private var start_location: String
    @SerializedName("delivery_location")
    @Expose
    private var delivery_location: String
    @SerializedName("price")
    @Expose
    private var price: String

    constructor(start_location: String, delivery_location: String, price: String) {
        this.start_location = start_location
        this.delivery_location = delivery_location
        this.price = price
    }
}
