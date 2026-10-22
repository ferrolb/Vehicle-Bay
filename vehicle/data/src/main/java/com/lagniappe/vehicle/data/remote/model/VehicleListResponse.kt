package com.lagniappe.vehicle.data.remote.model

import com.squareup.moshi.Json

data class VehicleListResponse(
    @Json(name = "@id")
    val id: String,
    val vin: String,
    val createdAt: String,
    val location: List<Double>,
    val vehicle: VehicleDetailsResponse?,
    val retailListing: RetailListingResponse,
    val history: VehicleHistoryResponse?
)

data class VehicleDetailsResponse(
    val baseInvoice: Int?,
    val baseMsrp: Int?,
    val bodyStyle: String,
    val confidence: Double?,
    val cylinders: Int?,
    val doors: Int?,
    val drivetrain: String,
    val engine: String,
    val exteriorColor: String,
    val fuel: String,
    val interiorColor: String,
    val make: String,
    val model: String,
    val seats: Int,
    val series: String?,
    val squishVin: String,
    val style: String?,
    val transmission: String,
    val trim: String?,
    val type: String?,
    val vin: String,
    val year: Int,
    val transmissionSource: String
)

data class RetailListingResponse(
    val carfaxUrl: String,
    val city: String,
    val cpo: Boolean,
    val dealer: String,
    val miles: Int,
    val photoCount: Int,
    val price: Int,
    val primaryImage: String,
    val state: String,
    val used: Boolean,
    val vdp: String,
    val zip: String?,
    val dealerId: String
)

data class VehicleHistoryResponse(
    val accidentCount: Int,
    val accidents: Boolean,
    val oneOwner: Boolean,
    val ownerCount: Int,
    val usageType: String
)
