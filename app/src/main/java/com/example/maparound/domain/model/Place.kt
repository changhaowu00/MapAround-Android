package com.example.maparound.domain.model

data class Place(
    val id: String,
    val image_url: String,
    val icon_url: String,
    val user_name: String,
    val title: String,
    val tag: String,
    val distance: String,
    val price: String?
)
