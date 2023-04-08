package com.example.maparound.ui.navigation

sealed class Screen (val route: String){
    object HomeScreen: Screen("home_screen")
}
