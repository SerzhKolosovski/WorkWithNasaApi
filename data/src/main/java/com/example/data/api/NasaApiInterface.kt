package com.example.data.api

import com.example.data.models.NasaData
import retrofit2.http.GET
import retrofit2.http.Path

interface NasaApiInterface {
    @GET("mars-photos/api/v1/rovers/{model_rover}/photos?sol=1000&api_key=$API_KEY")
    suspend fun getPhotosRover(
        @Path("model_rover") modelOfRover: String
    ): NasaData

    companion object {
        const val API_KEY = "5cV1SJ1HwYL4048FEQj1aEDHlr883yWuLtFJeR5o"
    }
}