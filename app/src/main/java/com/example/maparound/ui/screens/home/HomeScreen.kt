package com.example.maparound.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.maparound.ui.navigation.NavGraph
import com.example.maparound.ui.navigation.Screen
import com.example.maparound.ui.screens.ar.ArScreen
import com.example.maparound.ui.screens.home.PlaceMock.places
import com.example.maparound.ui.screens.home.components.BottomBar
import com.example.maparound.ui.screens.home.components.HomeListItem
import com.example.maparound.ui.screens.home.components.TopBar
import com.example.maparound.ui.screens.map.MapScreen
import com.example.maparound.ui.screens.map.MapScreenViewModel
import com.google.android.gms.location.LocationServices

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController : NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    mapViewModel: MapScreenViewModel = hiltViewModel()

){
    val context = LocalContext.current
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    mapViewModel.getDeviceLocationAsinc(fusedLocationProviderClient)

    Scaffold(
        topBar = { TopBar(navController)},
        bottomBar = { BottomBar(navController) }
    ) {

        //ScrollView()
        if (viewModel.selectedItemBottomBar.value == 1){
            MapScreen()
        }
        else if (viewModel.selectedItemBottomBar.value == 2){
            ArScreen()
        }
        else {
            ScrollView()
        }


    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}


@Composable
fun ScrollView(){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp, top = 106.dp)
    ) {
        items(places){place ->
            HomeListItem(place = place)
        }
    }
}
