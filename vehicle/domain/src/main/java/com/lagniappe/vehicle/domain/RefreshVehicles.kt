package com.lagniappe.vehicle.domain

import javax.inject.Inject

class RefreshVehicles
    @Inject
    constructor(
        private val vehicleRepository: VehicleRepository,
    ) {
        suspend operator fun invoke() {
            vehicleRepository.refreshVehicleData()
        }
    }
