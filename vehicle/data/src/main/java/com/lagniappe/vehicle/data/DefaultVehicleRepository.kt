package com.lagniappe.vehicle.data

import com.lagniappe.vehicle.data.remote.VehicleService
import com.lagniappe.vehicle.domain.VehicleRepository
import com.lagniappe.vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultVehicleRepository @Inject constructor(
    // TODO: should not be injecting the service directly, we should be injecting a data source that uses the service
    private val vehicleService: VehicleService
) : VehicleRepository {
    private val _vehicleList = MutableStateFlow<List<Vehicle>>(emptyList())
    val vehicleList: StateFlow<List<Vehicle>> = _vehicleList
    override fun getVehicleData(): Flow<List<Vehicle>> {
        return vehicleList
    }

    override suspend fun refreshVehicleData() {
        val resultList = vehicleService.getVehicleData().data.map { it.toVehicle() }
        _vehicleList.value = resultList
    }


}