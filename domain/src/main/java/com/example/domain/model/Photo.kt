package com.example.domain.model

data class Photo(
    val id: Int,
    val sol: Int,
    val camera: Camera,
    val imgSrc: String,
    val earthDate: String,
    val roverModelOf: Rover
)