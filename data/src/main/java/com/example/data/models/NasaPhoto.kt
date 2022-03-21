package com.example.data.models

import com.google.gson.annotations.SerializedName

data class NasaPhoto(
    @SerializedName("id") val id: Int,
    @SerializedName("sol") val sol: Int,
    @SerializedName("camera") val camera: RoverCamera,
    @SerializedName("img_src") val imgSrc: String,
    @SerializedName("earth_date") val earthDate: String,
    @SerializedName("rover") val roverModelOf: ModelOfNasaRover
)