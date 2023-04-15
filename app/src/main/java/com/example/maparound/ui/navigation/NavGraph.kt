package com.example.maparound.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.maparound.ui.screens.home.HomeScreen
import com.example.maparound.ui.screens.login.LoginScreen
import com.example.maparound.ui.screens.map.MapScreen
import com.example.maparound.ui.screens.register.RegisterScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        //startDestination = Screen.HomeScreen.route,
        startDestination = Screen.MapScreen.route,
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navHostController)
        }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navHostController)
        }
        composable(route = Screen.RegisterScreen.route){
            RegisterScreen(navHostController)
        }
        composable(route = Screen.MapScreen.route){
            MapScreen()
        }
    }
}