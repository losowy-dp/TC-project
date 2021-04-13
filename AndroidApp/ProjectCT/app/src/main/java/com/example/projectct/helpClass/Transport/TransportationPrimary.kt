package com.example.projectct.helpClass.Transport

data class TransportationPrimary(
        var id: String,
        var car_owner: String?=null,
        var data_created: String?=null,
        var start_location: String?=null,
        var description: String?=null,
        var delivery_location: String?=null,
        var price: String?=null,
        var currency: String?=null
)
