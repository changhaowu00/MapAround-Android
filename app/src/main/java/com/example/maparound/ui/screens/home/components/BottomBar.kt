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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.maparound.ui.navigation.Screen
import com.example.maparound.ui.screens.home.HomeScreen

@Composable
fun BottomBar(
    navController : NavHostController
){
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
                onClick = {
                    selectedItem = index
                    if(item == "Home"){
                        navController.navigate(Screen.HomeScreen.route)
                    }else if(item == "Crear"){
                        navController.navigate(Screen.LoginScreen.route)
                    }else if(item == "Mensajes"){
                        navController.navigate(Screen.LoginScreen.route)
                    }else if(item == "Perfil"){
                        navController.navigate(Screen.LoginScreen.route)
                    }

                }
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview(){
    BottomBar(rememberNavController())
}