package com.lagniappe.vehicle.data

import com.lagniappe.vehicle.data.remote.VehicleService
import com.lagniappe.vehicle.domain.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VehicleRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindVehicleRepository(
        repository: DefaultVehicleRepository
    ): VehicleRepository
}

@Module
@InstallIn(SingletonComponent::class)
object VehicleServiceModule {
    @Provides
    @Singleton
    fun provideVehicleService(retrofit: Retrofit): VehicleService =
        retrofit.create(VehicleService::class.java)
}
