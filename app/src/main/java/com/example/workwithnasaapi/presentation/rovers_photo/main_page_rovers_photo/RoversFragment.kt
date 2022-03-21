package com.example.workwithnasaapi.presentation.rovers_photo.main_page_rovers_photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.workwithnasaapi.databinding.FragmentRoversBinding


class RoversFragment : Fragment() {
    private var _binding: FragmentRoversBinding? = null
    private val binding: FragmentRoversBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRoversBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonOpportunity.setOnClickListener {
                findNavController()
                    .navigate(RoversFragmentDirections.toListPhotosFragment("opportunity"))
            }
            buttonSpirit.setOnClickListener {
                findNavController()
                    .navigate(RoversFragmentDirections.toListPhotosFragment("spirit"))
            }
            buttonCuriosity.setOnClickListener {
                findNavController()
                    .navigate(RoversFragmentDirections.toListPhotosFragment("curiosity"))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}