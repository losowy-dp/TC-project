package com.example.projectct.helpClass.Transport

data class TransportationPrimary(
        var id: String,
        var car_owner: String?=null,
        var data_created: String?=null,
        var start_location: String?=null,
        var model: String?=null,
        var description: String?=null,
        var delivery_location: String?=null,
        var price: String?=null,
        var currency: String?=null,
        var data_start_deliveri:String?= null,
        var data_end_deliveri: String?= null,
        var data_start_shipment: String?= null,
        var data_end_shipment: String?= null,
        var photo: String,
        var typeCar: String?=null,
        var punktA_1: String?=null,
        var punktA_2: String?=null,
        var punktB_1: String?=null,
        var punktB_2: String?=null
)
