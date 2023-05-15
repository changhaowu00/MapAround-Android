package com.example.maparound.ui.screens.map

import android.location.Location
import com.google.android.gms.maps.model.LatLng

data class MapScreenState(
    val lastKnownLocation: Location?,
    val currentLocation: LatLng =  LatLng(40.3320552,-3.7673026)
)
