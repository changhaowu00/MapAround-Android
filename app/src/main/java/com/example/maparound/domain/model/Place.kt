package com.example.maparound.domain.model

import android.media.Image

data class Place(
    val id: String,
    val image: String,
    val user_name: String,
    val title: String,
    val tag: String,
    val distance: String,
    val price: Double?
)
