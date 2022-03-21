package com.example.data.models

import com.google.gson.annotations.SerializedName

data class RoverCamera(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rover_id") val roverId: Int,
    @SerializedName("full_name") val fullName: String
)