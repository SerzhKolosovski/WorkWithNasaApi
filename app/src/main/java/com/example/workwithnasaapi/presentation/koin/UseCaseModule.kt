package com.example.workwithnasaapi.presentation.koin

import com.example.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPhotosRoverUseCase(get()) }
}