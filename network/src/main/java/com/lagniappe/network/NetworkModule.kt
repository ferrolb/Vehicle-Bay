package com.lagniappe.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // TODO: Replace with your actual base URL
    private const val BASE_URL = "https://api.auto.dev/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi
            .Builder()
            .add(StringOrNumberAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("vehicleApiKey") apiKey: String,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                Interceptor { chain ->
                    val request =
                        chain
                            .request()
                            .newBuilder()
                            .header("Authorization", "Bearer $apiKey")
                            .build()
                    chain.proceed(request)
                },
            ).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}

// Custom Moshi adapter to handle fields that can be either a String or a Number
// the vehicle API returns model as string in some cases and an int in others.
class StringOrNumberAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): String =
        when (reader.peek()) {
            JsonReader.Token.NUMBER -> {
                // Read as double or long, then convert to string
                reader.nextDouble().toString()
            }

            JsonReader.Token.STRING -> {
                reader.nextString()
            }

            else -> {
                reader.skipValue()
                ""
            }
        }
}
