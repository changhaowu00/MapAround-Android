package com.example.maparound.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.maparound.domain.model.Place
import com.example.maparound.R

@Composable
fun HomeListItem(place : Place){
    Card(modifier = Modifier.padding(2.dp)){
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray)
                .padding(2.dp)
        )
        Icon(
            painter = painterResource(R.drawable.),
            contentDescription = null)


    }
    """
    AsyncImage(
        model = place.image,
        contentDescription = null
    )
    """
}

@Preview
@Composable
fun HomeListItemPreview(){
    val place1 = Place(
        id = "1",
        image = "https://www.adslzone.net/app/uploads-adslzone.net/2019/04/borrar-fondo-imagen.jpg",
        user_name = "Paco",
        title = "Bar de Manolo Leganés",
        tag = "Bar",
        distance = "24m",
        price = null
    )
    val place2 = Place(
        id = "1",
        image = "https://www.adslzone.net/app/uploads-adslzone.net/2019/04/borrar-fondo-imagen.jpg",
        user_name = "Paco",
        title = "Bar de Manolo Leganés",
        tag = "Bar",
        distance = "24m",
        price = 123.0
    )

    HomeListItem(place2)
}