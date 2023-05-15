package com.example.maparound.ui.screens.map

import android.app.Activity.RESULT_OK
import android.content.Context
import android.location.Location
import androidx.activity.result.IntentSenderRequest
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.maparound.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import android.content.IntentSender
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    viewModel.getDeviceLocationAsinc(fusedLocationProviderClient)
    val isMyLocationEnabled = viewModel.getLastKnownLocation() != null

    //Request turn on location
    val settingResultRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK)
            Log.d("appDebug", "Accepted")
        else {
            Log.d("appDebug", "Denied")
        }
    }

    //Configure Google maps
    val theme = if (isSystemInDarkTheme()) R.raw.map_style_dark else R.raw.map_style_light
    var uiSettings by remember {
        mutableStateOf(MapUiSettings(
            compassEnabled = true,
            indoorLevelPickerEnabled = true,
            mapToolbarEnabled = true,
            myLocationButtonEnabled = true,
            rotationGesturesEnabled = true,
            scrollGesturesEnabled = true,
            scrollGesturesEnabledDuringRotateOrZoom = true,
            tiltGesturesEnabled = true,
            zoomControlsEnabled = false,
            zoomGesturesEnabled = true,
        ))
    }
    var properties by remember {
        mutableStateOf(MapProperties(
            isMyLocationEnabled = isMyLocationEnabled,
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, theme),
            mapType = MapType.NORMAL,//MapType.SATELLITE,
            maxZoomPreference = 18.0f,
            minZoomPreference = 15.8f,

        ))
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.state.value.currentLocation, 8f)
    }


    if (!isMyLocationEnabled){
        //TODO If not Center to the nearest place
    }
/*
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        CameraPositionState.centerOnLocation()
    }*/

    //Box(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp, top = 106.dp)){}


    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 55.dp, top = 106.dp),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings,
        onMapLoaded = {
            //Ask for enabling location enabled
            if (isMyLocationEnabled){
                checkLocationSetting(
                    context = context,
                    onDisabled = { intentSenderRequest ->
                        settingResultRequest.launch(intentSenderRequest)
                    },
                    onEnabled = { /* This will call when setting is already enabled */ }
                )
                coroutineScope.launch {
                    viewModel.state.value.lastKnownLocation?.let { cameraPositionState.centerOnLocation(it) }
                }
            }

        },
    ) {
        if (viewModel.state.value.currentLocation != null){
            Circle(
                center = viewModel.state.value.currentLocation,
                clickable = false,
                fillColor = if(isSystemInDarkTheme()) Color(0x1BCAEAFF)
                            else Color(0x1962BBF5),
                radius = 400.0,
                strokeColor = if(isSystemInDarkTheme()) Color(0x7ACAEAFF)
                else Color(0x8062BBF5),
                strokeWidth = 8f,
            )
        }

        Marker(
            state = MarkerState(position = viewModel.state.value.currentLocation),
            title = "Singapore",
            snippet = "Marker in Singapore",
            icon = Icons.Filled.Home,

        )


    }
}

/**
 * If you want to center on a specific location.
 */
suspend fun CameraPositionState.centerOnLocation(
    location: Location
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        LatLng(location.latitude, location.longitude), 16f),
)

/**
 * Center to my location
 */
fun centerToMyLocation(){

}



/**
 * Ask for tunrning on location
 */
fun checkLocationSetting(
    context: Context,
    onDisabled: (IntentSenderRequest) -> Unit,
    onEnabled: () -> Unit
) {

    val locationRequest = LocationRequest.create().apply {
        interval = 1000
        fastestInterval = 1000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val client: SettingsClient = LocationServices.getSettingsClient(context)
    val builder: LocationSettingsRequest.Builder = LocationSettingsRequest
        .Builder()
        .addLocationRequest(locationRequest)

    val gpsSettingTask: Task<LocationSettingsResponse> =
        client.checkLocationSettings(builder.build())

    gpsSettingTask.addOnSuccessListener { onEnabled() }
    gpsSettingTask.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                val intentSenderRequest = IntentSenderRequest
                    .Builder(exception.resolution)
                    .build()
                onDisabled(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {
                // ignore here
            }
        }
    }

}


@Preview
@Composable
fun MapScreenPreview(){
    MapScreen()
}