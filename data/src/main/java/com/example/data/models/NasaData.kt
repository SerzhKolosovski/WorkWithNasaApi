package com.example.data.models

import com.google.gson.annotations.SerializedName

data class NasaData(
    @SerializedName("photos") val photos: List<NasaPhoto> = emptyList()
)