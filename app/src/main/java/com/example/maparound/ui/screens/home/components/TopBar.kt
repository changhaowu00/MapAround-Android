package com.example.maparound.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.maparound.R

@Composable
fun TopBar(){
    Column() {
        //Top Bar
        var selectedItemTopBar by remember { mutableStateOf(0) }

        /*Row(modifier = Modifier.fillMaxWidth().height(50.dp)) {

            SearchBar(modifier = Modifier.height(40.dp))
        }*/

        NavigationBar(modifier = Modifier.height(65.dp)) {
            SearchBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(50.dp)
                    .align(Alignment.CenterVertically))

            NavigationBarItem(
                icon = { Icon(
                    painter = painterResource(R.drawable.ic_baseline_tune_24),
                    contentDescription = null) },
                selected = false,
                onClick = {  },
            )
        }

        //Divider
        Divider()

        //Bottom Bar
        var selectedItemBottomBar by remember { mutableStateOf(0) }
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
                    selected = selectedItemBottomBar == index,
                    onClick = { selectedItemBottomBar = index }
                )
            }
        }

    }
}

@Preview
@Composable
fun TopBarPreview(){
    TopBar()
}

