package com.example.workwithnasaapi.presentation.nasa_observatory.observatories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.workwithnasaapi.R
import com.example.workwithnasaapi.databinding.FragmentSecondObservatoryBinding
import com.example.workwithnasaapi.presentation.nasa_observatory.MainFragmentNasaObservatoryDirections


class SecondObservatory : Fragment() {
    private val coordinates = floatArrayOf(30.54854315323467F, -90.78166016931712F)
    private var _binding: FragmentSecondObservatoryBinding? = null
    private val binding: FragmentSecondObservatoryBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSecondObservatoryBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameOfObservatory = getString(R.string.ligo_laser_observatory)
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