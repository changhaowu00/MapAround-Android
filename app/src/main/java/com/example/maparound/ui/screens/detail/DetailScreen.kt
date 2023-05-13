package com.example.maparound.ui.screens.detail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.maparound.R
import com.example.maparound.domain.model.Place
import com.example.maparound.ui.screens.detail.components.ImageCarousel
import com.example.maparound.ui.screens.home.PlaceMock
import com.example.maparound.ui.screens.home.PlaceMock.places
import com.example.maparound.ui.screens.home.components.HomeListItem


@Composable
fun DetailScreen(
    navController : NavHostController,
    place : Place
){

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            //Carrousel
            item { ImageCarousel(place) }


        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkTheme")
@Composable
fun DetailScreenPreview(){
    DetailScreen(rememberNavController(), PlaceMock.places[0])
}

