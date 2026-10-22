package com.lagniappe.vehiclebay

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {
    @Provides
    @Singleton
    @Named("vehicleApiKey")
    fun provideVehicleApiKey(): String = BuildConfig.VEHICLE_API_KEY
}
