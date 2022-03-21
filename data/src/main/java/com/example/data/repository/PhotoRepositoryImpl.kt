package com.example.data.repository

import com.example.data.api.NasaApiInterface
import com.example.data.mapper.toDomainModel
import com.example.domain.model.Photo
import com.example.domain.repository.PhotoRepository

class PhotoRepositoryImpl(private val nasaApiInterface: NasaApiInterface) : PhotoRepository {
    override suspend fun getPhotosRover(modelOfRover: String): Result<List<Photo>> = runCatching {
        nasaApiInterface.getPhotosRover(modelOfRover)
    }.map { nasaApi -> nasaApi.photos.map {
        val nasaPhoto = it
        nasaPhoto.camera.toDomainModel()
        nasaPhoto.roverModelOf.toDomainModel()
        nasaPhoto.toDomainModel()
    } }

//    override suspend fun getCameraRover(photo: Photo,modelOfRover: String): Result<Camera> = runCatching {
//        nasaApiInterface.getPhotosRover(modelOfRover).photos.map { nasaPhoto ->  }
//    }.map { nasaPhoto -> nasaPhoto.camera.toDomainModel()}
//    suspend fun getCameraRover(): Result<Camera> = runCatching {
//        nasaApiInterface.getPhotosRover()
//    }.map { nasaApi -> nasaApi.photos.asSequence().map { it.toDomainModel() }.first { it.camera == modelOfRover } }
}

//override suspend fun getPhotosRover(modelOfRover: String): Result<List<Photo>> = runCatching {
//    nasaApiInterface.getPhotosRover(modelOfRover)
//}.map { nasaApi -> nasaApi.photos.map { it.toDomainModel() } }

