package com.example.maparound.ui.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.maparound.domain.model.Place
import com.example.maparound.ui.screens.detail.DetailScreen
import com.example.maparound.ui.screens.home.PlaceMock

@Composable
fun ImageCarousel(place: Place = PlaceMock.places[0]){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(MaterialTheme.colorScheme.surface)
            //.padding(1.dp)
    ){
        Box(modifier = Modifier.align(Alignment.Center)){
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = place.image_url,
                contentDescription = null
            )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.inverseOnSurface,RoundedCornerShape(100.dp)),

            content = {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back Button",
                    modifier = Modifier.size(80.dp))
            }
        )
    }

}

@Preview
@Composable
fun DetailScreenPreview(){
    ImageCarousel()
}