package com.lagniappe.vehiclebay

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.lagniappe.vehicle.data.remote.VehicleService
import com.lagniappe.vehiclebay.ui.theme.VehicleBayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        testVehicleCall()
        setContent {
            VehicleBayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    // TODO: Remove this test function once the VehicleService is moved to the vehicle/data module and tested there
    private fun testVehicleCall() {
        val service = retrofit.create(VehicleService::class.java)

        lifecycleScope.launch {
            try {
                val response = service.getVehicleData()
                Log.d("VehicleTest", "Got ${response.data.size} vehicles")
                Log.d("VehicleTest", "First vehicle: ${response.data.firstOrNull()}")
            } catch (e: Exception) {
                Log.e("VehicleTest", "Vehicle request or JSON parsing failed", e)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VehicleBayTheme {
        Greeting("Android")
    }
}