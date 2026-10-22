package com.lagniappe.vehicle.data

import com.lagniappe.vehicle.data.remote.model.RetailListingResponse
import com.lagniappe.vehicle.data.remote.model.VehicleDetailsResponse
import com.lagniappe.vehicle.data.remote.model.VehicleHistoryResponse
import com.lagniappe.vehicle.data.remote.model.VehicleListResponse
import com.lagniappe.vehicle.domain.model.RetailListing
import com.lagniappe.vehicle.domain.model.Vehicle
import com.lagniappe.vehicle.domain.model.VehicleDetails
import com.lagniappe.vehicle.domain.model.VehicleHistory

fun VehicleListResponse.toVehicle(): Vehicle {
    return Vehicle(
        id = id,
        vin = vin,
        createdAt = createdAt,
        location = location.map{it},
        vehicle = vehicle?.toVehicleDetails(),
        retailListing = retailListing.toRetailListing(),
        history = history?.toVehicleHistory()
    )
}

fun VehicleDetailsResponse.toVehicleDetails(): VehicleDetails {
    return VehicleDetails(
        baseInvoice = baseInvoice,
        baseMsrp = baseMsrp,
        bodyStyle = bodyStyle,
        confidence = confidence,
        cylinders = cylinders,
        doors = doors,
        drivetrain = drivetrain,
        engine = engine,
        exteriorColor = exteriorColor,
        fuel = fuel,
        interiorColor = interiorColor,
        make = make,
        model = model,
        seats = seats,
        series = series,
        squishVin = squishVin,
        style = style,
        transmission = transmission,
        trim = trim,
        type = type,
        vin = vin,
        year = year,
        transmissionSource = transmissionSource
    )
}

fun RetailListingResponse.toRetailListing(): RetailListing {
    return RetailListing(
        carfaxUrl = carfaxUrl,
        city = city,
        cpo = cpo,
        dealer = dealer,
        miles = miles,
        photoCount = photoCount,
        price = price,
        primaryImage = primaryImage,
        state = state,
        used = used,
        vdp = vdp,
        zip = zip,
        dealerId = dealerId
    )
}

fun VehicleHistoryResponse.toVehicleHistory(): VehicleHistory {
    return VehicleHistory(
        accidentCount = accidentCount,
        accidents = accidents,
        oneOwner = oneOwner,
        ownerCount = ownerCount,
        usageType = usageType
    )
}
