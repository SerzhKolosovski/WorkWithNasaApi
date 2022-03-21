package com.example.data.mapper

import com.example.data.models.NasaPhoto
import com.example.data.models.ModelOfNasaRover
import com.example.data.models.RoverCamera
import com.example.domain.model.Camera
import com.example.domain.model.Photo
import com.example.domain.model.Rover

internal fun NasaPhoto.toDomainModel(): Photo {
    return Photo(
        id = id,
        sol = sol,
        camera = camera.toDomainModel(),
        imgSrc = imgSrc,
        earthDate = earthDate,
        roverModelOf = roverModelOf.toDomainModel()
    )
}

internal fun RoverCamera.toDomainModel(): Camera {
    return Camera(
        id = id,
        fullName = fullName,
        name = name,
        roverId = roverId
    )
}

internal fun ModelOfNasaRover.toDomainModel(): Rover {
    return Rover(
        id = id,
        name = name,
        landingDate = landingDate,
        launchDate = launchDate,
        status = status
    )
}