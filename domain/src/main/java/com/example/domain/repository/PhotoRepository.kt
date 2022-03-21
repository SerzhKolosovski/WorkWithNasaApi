package com.example.domain.repository

import com.example.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotosRover(modelOfRover: String): Result<List<Photo>>
}