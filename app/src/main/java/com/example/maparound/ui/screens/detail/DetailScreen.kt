package com.example.maparound.ui.screens.detail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.maparound.domain.model.Place
import com.example.maparound.ui.screens.detail.components.ImageCarousel
import com.example.maparound.ui.screens.home.PlaceMock


@Composable
fun DetailScreen(
    navController : NavHostController,
    place : Place
){

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            //Carrousel
            item { ImageCarousel(place) }
            item{Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.Black))}
        }
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkTheme")
@Composable
fun DetailScreenPreview(){
    DetailScreen(rememberNavController(), PlaceMock.places[0])
}

