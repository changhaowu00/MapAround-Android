package com.example.maparound.ui.screens.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maparound.R
import com.example.maparound.domain.model.Place

@Composable
fun TitleInfo(place : Place){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        shape = RoundedCornerShape(0.dp)
    ){
        
        Row(
            Modifier
                .fillMaxWidth()
                .padding(3.dp)){

            Column(Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(7.dp)
                ){
                    Icon(
                        painter = painterResource(place.marker),
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
                    fontSize = 24.sp,
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

        var selectedItem by remember { mutableStateOf(0) }
        val items = listOf("Guardar", "Enviar Mensaje", "Ver Mapa")
        val icons = listOf(
            R.drawable.bookmark,
            R.drawable.send_msg,
            R.drawable.map
        )
        NavigationBar(
            containerColor= Color.Transparent,
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(
                        painter = painterResource(icons.get(index)),
                        modifier = Modifier.size(20.dp),
                        contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == null,
                    onClick = { }
                )
            }
        }
    }

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