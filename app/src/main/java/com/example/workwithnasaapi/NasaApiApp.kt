package com.example.workwithnasaapi

import android.app.Application
import com.example.workwithnasaapi.presentation.koin.*
import com.example.workwithnasaapi.presentation.settings.context_extension.LanguageAwareAppContext
import com.example.workwithnasaapi.presentation.settings.sharedpref.Manager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NasaApiApp: Application() {

    private val manager: Manager by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NasaApiApp)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                appModule
            )
        }
    }
}