package com.lagniappe.vehicle.data

import com.lagniappe.vehicle.data.remote.VehicleService
import com.lagniappe.vehicle.data.remote.model.RetailListingResponse
import com.lagniappe.vehicle.data.remote.model.VehicleDataResponse
import com.lagniappe.vehicle.data.remote.model.VehicleDetailsResponse
import com.lagniappe.vehicle.data.remote.model.VehicleHistoryResponse
import com.lagniappe.vehicle.data.remote.model.VehicleListResponse
import com.lagniappe.vehicle.domain.model.RetailListing
import com.lagniappe.vehicle.domain.model.Vehicle
import com.lagniappe.vehicle.domain.model.VehicleDetails
import com.lagniappe.vehicle.domain.model.VehicleHistory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultVehicleRepositoryTest {
    @Test
    fun `getVehicleData returns mapped vehicles from service`() = runBlocking {
        val responseVehicle = VehicleListResponse(
            id = "https://api.auto.dev/listings/1HGBH41JXMN109186",
            vin = "1HGBH41JXMN109186",
            createdAt = "2026-10-22 09:00:00",
            location = listOf(-80.0, 25.0),
            vehicle = VehicleDetailsResponse(
                baseInvoice = 20000,
                baseMsrp = 22000,
                bodyStyle = "Sedan",
                confidence = 0.99,
                cylinders = 4,
                doors = 4,
                drivetrain = "FWD",
                engine = "2.0L 4Cyl Gasoline",
                exteriorColor = "Blue",
                fuel = "Gasoline",
                interiorColor = "Black",
                make = "Honda",
                model = "Civic",
                seats = 5,
                series = "LX",
                squishVin = "1HGBH41JMN",
                style = "4dr Sedan",
                transmission = "Automatic",
                trim = "LX",
                type = "Car",
                vin = "1HGBH41JXMN109186",
                year = 2021,
                transmissionSource = "listing"
            ),
            retailListing = RetailListingResponse(
                carfaxUrl = "https://example.com/carfax",
                city = "Miami",
                cpo = false,
                dealer = "Example Honda",
                miles = 25000,
                photoCount = 12,
                price = 21000,
                primaryImage = "https://example.com/image.jpg",
                state = "FL",
                used = true,
                vdp = "https://example.com/vehicle",
                zip = "33101",
                dealerId = "dealer-1"
            ),
            history = VehicleHistoryResponse(
                accidentCount = 0,
                accidents = false,
                oneOwner = true,
                ownerCount = 1,
                usageType = "Personal Use"
            )
        )
        val repository = DefaultVehicleRepository(
            FakeVehicleService(VehicleDataResponse(data = listOf(responseVehicle)))
        )

        val result = repository.getVehicleData()

        assertEquals(
            listOf(
                Vehicle(
                    id = "https://api.auto.dev/listings/1HGBH41JXMN109186",
                    vin = "1HGBH41JXMN109186",
                    createdAt = "2026-10-22 09:00:00",
                    location = listOf(-80.0, 25.0),
                    vehicle = VehicleDetails(
                        baseInvoice = 20000,
                        baseMsrp = 22000,
                        bodyStyle = "Sedan",
                        confidence = 0.99,
                        cylinders = 4,
                        doors = 4,
                        drivetrain = "FWD",
                        engine = "2.0L 4Cyl Gasoline",
                        exteriorColor = "Blue",
                        fuel = "Gasoline",
                        interiorColor = "Black",
                        make = "Honda",
                        model = "Civic",
                        seats = 5,
                        series = "LX",
                        squishVin = "1HGBH41JMN",
                        style = "4dr Sedan",
                        transmission = "Automatic",
                        trim = "LX",
                        type = "Car",
                        vin = "1HGBH41JXMN109186",
                        year = 2021,
                        transmissionSource = "listing"
                    ),
                    retailListing = RetailListing(
                        carfaxUrl = "https://example.com/carfax",
                        city = "Miami",
                        cpo = false,
                        dealer = "Example Honda",
                        miles = 25000,
                        photoCount = 12,
                        price = 21000,
                        primaryImage = "https://example.com/image.jpg",
                        state = "FL",
                        used = true,
                        vdp = "https://example.com/vehicle",
                        zip = "33101",
                        dealerId = "dealer-1"
                    ),
                    history = VehicleHistory(
                        accidentCount = 0,
                        accidents = false,
                        oneOwner = true,
                        ownerCount = 1,
                        usageType = "Personal Use"
                    )
                )
            ),
            result
        )
    }
}

private class FakeVehicleService(
    private val response: VehicleDataResponse
) : VehicleService {
    override suspend fun getVehicleData(): VehicleDataResponse = response
}
