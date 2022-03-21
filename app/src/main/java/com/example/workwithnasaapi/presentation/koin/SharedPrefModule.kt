package com.example.workwithnasaapi.presentation.koin

import com.example.workwithnasaapi.presentation.settings.sharedpref.Manager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Manager(androidContext()) }
}