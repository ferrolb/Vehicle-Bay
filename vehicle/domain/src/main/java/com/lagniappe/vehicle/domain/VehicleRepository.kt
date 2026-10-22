package com.lagniappe.vehicle.domain

import com.lagniappe.vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getVehicleData(): Flow<List<Vehicle>>

    suspend fun refreshVehicleData()
}