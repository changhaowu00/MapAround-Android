package com.example.maparound.ui.screens.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.maparound.ui.screens.home.HomeScreen

@Composable
fun BottomBar(){
    //Bottom Bar
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Crear", "Mensajes", "Perfil")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.AddCircle, Icons.Filled.Email, Icons.Filled.Person )
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons.get(index), contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview(){
    BottomBar()
}