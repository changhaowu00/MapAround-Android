package com.example.maparound.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel@Inject constructor( ) :ViewModel(){
    var selectedItemBottomBar = mutableStateOf(0)

    fun setSelectedItemBottomBar(value:Int){
        selectedItemBottomBar.value = value
    }
}