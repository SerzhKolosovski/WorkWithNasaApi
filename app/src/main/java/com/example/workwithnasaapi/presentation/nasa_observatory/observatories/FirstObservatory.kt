package com.example.workwithnasaapi.presentation.nasa_observatory.observatories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.workwithnasaapi.R
import com.example.workwithnasaapi.databinding.FragmentFirstObservatoryBinding
import com.example.workwithnasaapi.presentation.nasa_observatory.MainFragmentNasaObservatoryDirections
import com.example.workwithnasaapi.presentation.rovers_photo.main_page_rovers_photo.RoversFragmentDirections

class FirstObservatory : Fragment() {
    private val coordinates = floatArrayOf(-33.93442166653077F, 18.47712600321734F)
    private var _binding: FragmentFirstObservatoryBinding? = null
    private val binding: FragmentFirstObservatoryBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstObservatoryBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameOfObservatory = getString(R.string.south_african_astronomical_observatory)
        with(binding) {
            buttonToMap.setOnClickListener {
                findNavController().navigate(
                    MainFragmentNasaObservatoryDirections.toMapsFragment(coordinates,nameOfObservatory)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
