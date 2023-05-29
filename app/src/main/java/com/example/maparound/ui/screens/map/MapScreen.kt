package com.example.maparound.ui.screens.map

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.IntentSender
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.maparound.R
import com.example.maparound.domain.model.Place
import com.example.maparound.ui.screens.home.PlaceMock.loc
import com.example.maparound.ui.screens.home.PlaceMock.places
import com.example.maparound.ui.screens.home.components.HomeListItem
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.Task
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    viewModel.getDeviceLocationAsinc(fusedLocationProviderClient)
    val isMyLocationEnabled = viewModel.getLastKnownLocation() != null

    //BottomSheet
    val scaffoldState = rememberBottomSheetScaffoldState()

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

    var clickedPlace by remember {
        mutableStateOf(viewModel.state.value.clickedPlace)
    }

    //BottomSheetScafold
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetContent = {
            if( clickedPlace!=null){
                Box(modifier = Modifier.padding(bottom = 78.dp)){
                    HomeListItem(place = clickedPlace!!)
                }
            }

        }) { innerPadding ->

        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 55.dp, top = 106.dp),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings,
            onMapClick = {  coroutineScope.launch {scaffoldState.bottomSheetState.partialExpand()}},
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

            var index = 0
            for (place in places ) {
                loc[index].latitude
                val locat = LatLng(
                    viewModel.state.value.currentLocation.latitude +loc[index].latitude
                    ,
                    viewModel.state.value.currentLocation.longitude +loc[index].longitude
                )
                Marker(
                    state = MarkerState(position = locat),
                    alpha = 0.7f,
                    title = place.title,
                    snippet = place.distance+"-"+place.tag,
                    icon = getBitmapDescriptor(context,place.marker,R.color.marker),
                    onClick = { maker ->
                        coroutineScope.launch {
                            clickedPlace = place
                            CameraUpdateFactory.newLatLngZoom(locat, 16f)
                            scaffoldState.bottomSheetState.expand()
                        }
                        maker.setIcon(getBitmapDescriptor(context,place.marker,R.color.marker_selected))
                        maker.alpha=1.0f
                        false
                    },
                    onInfoWindowClose = {maker ->
                        maker.setIcon(getBitmapDescriptor(context,place.marker,R.color.marker))
                        maker.alpha=0.7f
                    }
                )
                index++
            }

        }
    }

}

fun getBitmapDescriptor(context: Context,id: Int,color: Int): BitmapDescriptor {
    return BitmapHelper.vectorToBitmap(context, id, ContextCompat.getColor(context, color))
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
object BitmapHelper {
    /**
     * Demonstrates converting a [Drawable] to a [BitmapDescriptor],
     * for use as a marker icon. Taken from ApiDemos on GitHub:
     * https://github.com/googlemaps/android-samples/blob/main/ApiDemos/kotlin/app/src/main/java/com/example/kotlindemos/MarkerDemoActivity.kt
     */
    fun vectorToBitmap(
        context: Context,
        @DrawableRes id: Int,
        @ColorInt color: Int
    ): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(context.resources, id, null)
        if (vectorDrawable == null) {
            Log.e("BitmapHelper", "Resource not found")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}





