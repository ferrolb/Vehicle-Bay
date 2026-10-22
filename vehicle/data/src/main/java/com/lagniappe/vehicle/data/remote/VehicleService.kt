package com.lagniappe.vehicle.data.remote

import com.lagniappe.vehicle.data.remote.model.VehicleDataResponse
import retrofit2.http.GET


interface VehicleService {
    @GET("listings")
    suspend fun getVehicleData(): VehicleDataResponse
}
