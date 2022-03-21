package com.example.workwithnasaapi.presentation.koin

import com.example.workwithnasaapi.presentation.rovers_photo.list_rovers_photos.ListPhotosViewModel
import com.example.workwithnasaapi.presentation.rovers_photo.photo_details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {(modelOfRover: String) ->  ListPhotosViewModel(get(),modelOfRover) }

    viewModel { DetailsViewModel(get()) }
}