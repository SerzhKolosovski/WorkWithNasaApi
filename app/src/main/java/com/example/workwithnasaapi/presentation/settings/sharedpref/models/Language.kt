package com.example.workwithnasaapi.presentation.settings.sharedpref.models

import androidx.annotation.StringRes
import com.example.workwithnasaapi.R
import java.util.*

enum class Language(
    @StringRes val titleRes: Int,
    val locale: Locale
) {
    EN(
        titleRes = R.string.language_button_en,
        locale = Locale.ENGLISH
    ),
    RU(
        titleRes = R.string.language_button_ru,
        locale = Locale("ru")
    )
}