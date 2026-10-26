package com.lagniappe.vehicle.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lagniappe.vehicle.domain.GetVehicles
import com.lagniappe.vehicle.domain.RefreshVehicles
import com.lagniappe.vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleListViewModel
    @Inject
    constructor(
        private val refreshVehicles: RefreshVehicles,
        getVehicleData: GetVehicles,
    ) : ViewModel() {
        val vehicleListState: StateFlow<List<Vehicle>> =
            getVehicleData()
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(5000),
                    emptyList(),
                )

        init {
            viewModelScope.launch {
                refreshVehicles()
            }
        }
    }
