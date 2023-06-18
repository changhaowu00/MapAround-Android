package com.example.maparound.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.maparound.domain.model.Place
import com.example.maparound.ui.screens.detail.components.DetailDescription
import com.example.maparound.ui.screens.detail.components.ImageCarousel
import com.example.maparound.ui.screens.detail.components.Products
import com.example.maparound.ui.screens.detail.components.TitleInfo
import com.example.maparound.ui.screens.home.PlaceMock


@Composable
fun DetailScreen(
    navController : NavHostController,
    place : Place
){

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            //Carrousel
            item { ImageCarousel(place, navController) }
            item { TitleInfo(place)}
            item { DetailDescription(place = place) }
            item { Products(place = place) }
        }
    }

}

@Preview
@Composable
fun DetailScreenPreview(){
    DetailScreen(rememberNavController(), PlaceMock.places[0])
}

