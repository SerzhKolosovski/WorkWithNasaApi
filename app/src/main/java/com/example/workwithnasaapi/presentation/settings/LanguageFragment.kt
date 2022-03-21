package com.example.workwithnasaapi.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.workwithnasaapi.databinding.FragmentLanguageBinding
import com.example.workwithnasaapi.presentation.settings.sharedpref.Manager
import com.example.workwithnasaapi.presentation.settings.sharedpref.models.Language
import org.koin.android.ext.android.inject

class LanguageFragment: Fragment() {
    private var _binding: FragmentLanguageBinding? = null
    private val binding: FragmentLanguageBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }
    private val manager: Manager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLanguageBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            when (manager.language) {
                Language.EN -> buttonEn
                Language.RU -> buttonRu
            }.isChecked = true

            radioGroup.setOnCheckedChangeListener { _, buttonId ->
                val language = when (buttonId) {
                    buttonEn.id -> Language.EN
                    buttonRu.id -> Language.RU
                    else -> error("incorrect buttonId $buttonId")
                }
                manager.language = language

                activity?.recreate()
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