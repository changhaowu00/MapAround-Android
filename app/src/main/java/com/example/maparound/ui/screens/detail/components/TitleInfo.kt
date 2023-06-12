package com.example.maparound.ui.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import coil.compose.AsyncImage
import com.example.maparound.R
import com.example.maparound.domain.model.Place

@Composable
fun TitleInfo(place : Place){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        shape = RoundedCornerShape(1.dp)
    ){

        Row(
            Modifier
                .fillMaxWidth()
                .padding(3.dp)){
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                //.padding(1.dp)
            ){
                AsyncImage(

                    contentScale = ContentScale.Crop,
                    model = place.image_url,
                    contentDescription = null
                )
            }
            Column(Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(7.dp)
                ){
                    Icon(
                        painter = painterResource(R.drawable.tag),
                        contentDescription = null,Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary)

                    Text(
                        text = place.tag,
                        color = MaterialTheme.colorScheme.primary)


                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.distance),
                        contentDescription = null,Modifier.size(20.dp),
                        tint =  MaterialTheme.colorScheme.tertiary)
                    Text(
                        text = place.distance,
                        color = MaterialTheme.colorScheme.tertiary)
                }

                Text(
                    text = place.title,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                if (place.date_time!=null){
                    Text(
                        text = place.date_time,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = MaterialTheme.colorScheme.secondary)
                }
                if (place.price!=null){
                    Text(
                        text = place.price,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = MaterialTheme.colorScheme.secondary)
                }


            }
        }

        Row(modifier = Modifier.height(30.dp).padding(vertical = 0.dp)) {
            Row(
                modifier = Modifier
                    //.width(130.dp)
                    .align( Alignment.CenterVertically)
                    .padding(start = 5.dp)
            ){
                Box(
                    modifier = Modifier
                        .size(21.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color.Gray)
                        .align( Alignment.CenterVertically)
                ){
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        model = place.icon_url,
                        contentDescription = null
                    )
                }

                Text(
                    text = if(place.user_name.length>15) "${place.user_name.subSequence(0,12)}..."
                    else place.user_name,
                    modifier = Modifier
                        .width(110.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    painter = painterResource(R.drawable.bookmark),
                    contentDescription = null,Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary)
            }

            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    painter = painterResource(R.drawable.send_msg),
                    contentDescription = null,Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary)
            }

            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    painter = painterResource(R.drawable.map),
                    contentDescription = null,Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary)
            }
        }
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
fun TitleInfoPreview(){
    val place1 = Place(
        id = "0",
        image_url = listOf<String>("https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/uc3m_mapAround.jpg"),
        icon_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/uc3m.png",
        user_name = "UC3M",
        title = "Defensa TFG Map Around",
        tag = "Evento",
        distance = "1m",
        price = "Gratis",
        date_time = "12/04/23, 12:00",
        publish_time = "23d",
        marker = R.drawable.event
    )

    TitleInfo(place1)
}