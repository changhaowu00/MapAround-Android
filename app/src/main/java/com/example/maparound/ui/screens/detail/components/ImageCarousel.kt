package com.example.maparound.ui.screens.detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.example.maparound.domain.model.Place
import com.example.maparound.ui.screens.home.PlaceMock
import com.example.maparound.ui.screens.home.PlaceMock.places
import com.google.accompanist.pager.*

import kotlin.math.absoluteValue


@Composable
fun ImageCarousel(place: Place = PlaceMock.places[0]){

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){

        //Carrousel
        Carousel()

        //Back Button
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(5.dp)
                .size(40.dp),
            content = {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxSize()
                        .padding(0.dp)//.size(80.dp)
                )
            }
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Carousel() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = 4,
        state = pagerState,
    ) { page ->
        Card(
            Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions

                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            // Card content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.CenterHorizontally)
            ){
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillHeight,
                    model = places[page].image_url,
                    contentDescription = null
                )
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 210.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp).align(Alignment.CenterHorizontally)
        )
    }
}



@Preview
@Composable
fun DetailScreenPreview(){
    ImageCarousel()
}