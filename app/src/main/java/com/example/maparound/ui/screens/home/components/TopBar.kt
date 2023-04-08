package com.example.maparound.ui.screens.home.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.maparound.R

@Composable
fun TopBar(){
    //TopSide

    //Divider

    //DownSide
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Scroll Visualization", "Map Visualization", "AR Visualization")
    val icons = listOf(
        R.drawable.ic_baseline_view_list_24,
        R.drawable.ic_baseline_map_24,
        R.drawable.ic_baseline_view_in_ar_24)
    NavigationBar(
        modifier = Modifier.height(40.dp),
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(
                    painter = painterResource(icons.get(index)),
                    contentDescription = item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }

}

@Preview
@Composable
fun TopBarPreview(){
    TopBar()
}

