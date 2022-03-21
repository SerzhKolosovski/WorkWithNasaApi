package com.example.workwithnasaapi.presentation.koin

import com.example.data.repository.PhotoRepositoryImpl
import com.example.domain.repository.PhotoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PhotoRepository> { PhotoRepositoryImpl(get()) }
}