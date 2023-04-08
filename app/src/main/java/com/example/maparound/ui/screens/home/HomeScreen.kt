package com.example.maparound.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.maparound.ui.screens.home.components.BottomBar
import com.example.maparound.ui.screens.home.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Scaffold(
        topBar = { TopBar()},
        bottomBar = { BottomBar() }
    ) {

    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}