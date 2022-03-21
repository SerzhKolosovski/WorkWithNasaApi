package com.example.workwithnasaapi.presentation.rovers_photo.list_rovers_photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Photo
import com.example.domain.usecase.GetPhotosRoverUseCase
import com.example.workwithnasaapi.presentation.model.State
import kotlinx.coroutines.flow.*

class ListPhotosViewModel(
    private val getPhotosRoverUseCase: GetPhotosRoverUseCase,
    private val modelOfRover: String
) : ViewModel() {
    val photoFlow: Flow<State<List<Photo>>> =
        flow {
            val lceState = getPhotosRoverUseCase(modelOfRover)
                .fold(
                    onSuccess = {
                        State.Content(it)
                    },
                    onFailure = {
                        State.Error(it)
                    }
                )
            emit(lceState)
        }.onStart {
            emit(State.Loading)
        }.shareIn(viewModelScope, started = SharingStarted.Eagerly, replay = 1)
}
