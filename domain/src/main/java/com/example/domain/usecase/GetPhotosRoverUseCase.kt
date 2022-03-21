package com.example.domain.usecase

import com.example.domain.model.Photo
import com.example.domain.repository.PhotoRepository

class GetPhotosRoverUseCase(private val photoRepository: PhotoRepository) {

    suspend operator fun invoke(modelOfRover: String): Result<List<Photo>> {
        return photoRepository.getPhotosRover(modelOfRover)
    }
}
