package com.example.workwithnasaapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.workwithnasaapi.R
import com.example.workwithnasaapi.databinding.FragmentMainPageBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainPageBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonPhotos.setOnClickListener {
                findNavController().navigate(R.id.to_roversFragment)
            }
            nasaObservatory.setOnClickListener {
                findNavController().navigate(R.id.to_mainFragmentNasaObservatory)
            }
            buttonStarMap.setOnClickListener {
                findNavController().navigate(R.id.to_settingFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}