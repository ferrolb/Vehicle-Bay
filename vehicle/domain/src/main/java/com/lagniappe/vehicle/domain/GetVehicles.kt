package com.lagniappe.vehicle.domain

import com.lagniappe.vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehicles
    @Inject
    constructor(
        private val vehicleRepository: VehicleRepository,
    ) {
        operator fun invoke(): Flow<List<Vehicle>> = vehicleRepository.getVehicleData()
    }
