package com.example.maparound.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.maparound.ui.screens.home.HomeScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route,
    ) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }

    }
}