package com.example.workwithnasaapi.presentation.settings.sharedpref

import android.content.Context
import com.example.workwithnasaapi.presentation.settings.sharedpref.models.Language
import com.example.workwithnasaapi.presentation.settings.sharedpref.models.NightMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Manager(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var nightMode: NightMode by enumPref(KEY_NIGHT_MODE, NightMode.SYSTEM)

    var language: Language by Delegate(
        sharedPrefs,
        getValue = {
            getString(KEY_LANGUAGE, null)
                ?.let { enumValueOf<Language>(it) }
                ?: Language.EN
        },
        setValue = {
            putString(KEY_LANGUAGE, it.name)
            _languageFlow.tryEmit(it)
        }
    )

    private val _languageFlow = MutableStateFlow(language)
    val languageFlow: Flow<Language> = _languageFlow.asStateFlow()

    private inline fun <reified E : Enum<E>> enumPref(key: String, defaultValue: E) =
        Delegate(
            sharedPrefs,
            getValue = { getString(key, null)?.let(::enumValueOf) ?: defaultValue },
            setValue = { putString(key, it.name) }
        )

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val KEY_NIGHT_MODE = "night_mode"
        private const val KEY_LANGUAGE = "locale"
    }
}