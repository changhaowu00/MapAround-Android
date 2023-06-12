package com.example.maparound.ui.navigation

sealed class Screen (val route: String){
    object HomeScreen: Screen("home_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MapScreen: Screen("map_screen")
    object ArScreen: Screen("ar_screen")
    object DetailScreen: Screen("detail_screen")
}
