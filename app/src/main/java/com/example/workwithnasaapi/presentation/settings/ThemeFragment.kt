package com.example.workwithnasaapi.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.workwithnasaapi.databinding.FragmentThemeBinding
import com.example.workwithnasaapi.presentation.settings.sharedpref.Manager
import com.example.workwithnasaapi.presentation.settings.sharedpref.models.NightMode
import org.koin.android.ext.android.inject

class ThemeFragment: Fragment() {
    private var _binding: FragmentThemeBinding? = null
    private val binding: FragmentThemeBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val manager: Manager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentThemeBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            when (manager.nightMode) {
                NightMode.DARK -> buttonDarkMode
                NightMode.LIGHT -> buttonLightMode
                NightMode.SYSTEM -> buttonSystemMode
            }.isChecked = true

            radioGroup.setOnCheckedChangeListener { _, buttonId ->
                val (prefsMode, systemMode) = when (buttonId) {
                    buttonDarkMode.id -> NightMode.DARK to AppCompatDelegate.MODE_NIGHT_YES
                    buttonLightMode.id -> NightMode.LIGHT to AppCompatDelegate.MODE_NIGHT_NO
                    buttonSystemMode.id -> NightMode.SYSTEM to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    else -> error("incorrect buttonId $buttonId")
                }

                manager.nightMode = prefsMode
                AppCompatDelegate.setDefaultNightMode(systemMode)
            }

            ViewCompat.setOnApplyWindowInsetsListener(root) { _, insets ->
                appBar.updatePadding(
                    top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
                )
                insets
            }

            toolbar.setupWithNavController(findNavController())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}