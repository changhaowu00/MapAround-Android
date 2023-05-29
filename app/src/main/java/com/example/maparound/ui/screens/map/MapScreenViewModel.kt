package com.example.maparound.ui.screens.map


import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Tasks.await
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel@Inject constructor() : ViewModel(){

    val state: MutableState<MapScreenState> = mutableStateOf(
        MapScreenState(
            lastKnownLocation = null,
            clickedPlace = null
        )
    )

    fun getLastKnownLocation(): Location?{
        return state.value.lastKnownLocation
    }

    fun getDeviceLocationAsinc(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */

        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = state.value.copy(
                        lastKnownLocation = task.result,
                        currentLocation = LatLng(task.result.latitude,task.result.longitude)
                    )
                }
            }

        } catch (e: SecurityException) {
            // Show error or something
        }
    }

}