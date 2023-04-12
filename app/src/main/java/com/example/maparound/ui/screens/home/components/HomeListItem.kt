package com.example.maparound.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.maparound.domain.model.Place
import com.example.maparound.R

@Composable
fun HomeListItem(place : Place){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ){

        Row(
            Modifier
                .fillMaxWidth()
                .padding(3.dp)){
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
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

                if (place.price!=null){
                    Text(
                        text = place.price,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        MaterialTheme.colorScheme.secondary)
                }

            }
        }

        Row(modifier = Modifier.height(30.dp).padding(vertical = 0.dp)) {
            Row(
                modifier = Modifier
                    .width(130.dp)
                    .align( Alignment.CenterVertically)
                    .padding(horizontal = 3.dp)
            ){
                Box(
                    modifier = Modifier
                        .size(19.dp)
                        .clip(RoundedCornerShape(10.dp))
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
fun HomeListItemPreview(){
    val place1 = Place(
        id = "1",
        image_url = "https://cultibar.files.wordpress.com/2015/09/el-manolo-estc3a9tica-actual-esencia-eterna.jpg",
        icon_url = "https://media.timeout.com/images/101415021/image.jpg",
        user_name = "Paco",
        title = "Bar de Manolo Leganés",
        tag = "Bar",
        distance = "24m",
        price = null
    )
    val place2 = Place(
        id = "1",
        image_url = "https://cultibar.files.wordpress.com/2015/09/el-manolo-estc3a9tica-actual-esencia-eterna.jpg",
        icon_url = "https://media.timeout.com/images/101415021/image.jpg",
        user_name = "Paco Manolito de Leganes",
        title = "Bar de Manolo Leganés",
        tag = "Bar",
        distance = "24m",
        price = "123.0€"
    )

    HomeListItem(place2)
}