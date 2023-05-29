package com.example.maparound.ui.screens.map

import android.location.Location
import com.example.maparound.domain.model.Place
import com.google.android.gms.maps.model.LatLng

data class MapScreenState(
    var lastKnownLocation: Location?,
    var currentLocation: LatLng =  LatLng(40.3320552,-3.7673026),
    var clickedPlace: Place?
)
