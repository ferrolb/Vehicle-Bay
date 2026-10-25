package com.lagniappe.vehicle.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VehicleListScreen(modifier: Modifier = Modifier) {
    val vehicles = List(10) { index -> "Vehicle ${index + 1}" }

    LazyColumn(modifier = modifier) {
        items(vehicles) { vehicle ->
            Text(text = vehicle)
        }
    }
}
