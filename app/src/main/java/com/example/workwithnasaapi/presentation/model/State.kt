package com.example.workwithnasaapi.presentation.model

sealed class State<out T> {
    object Loading : State<Nothing>()

    data class Content<T>(val value: T) : State<T>()

    data class Error(val throwable: Throwable) : State<Nothing>()
}
