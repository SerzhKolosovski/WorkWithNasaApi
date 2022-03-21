package com.example.workwithnasaapi.presentation.nasa_observatory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.workwithnasaapi.R
import com.example.workwithnasaapi.databinding.FragmentMainNasaObservatoryBinding
import com.example.workwithnasaapi.presentation.nasa_observatory.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainFragmentNasaObservatory: Fragment() {

    private var _binding: FragmentMainNasaObservatoryBinding? = null
    private val binding: FragmentMainNasaObservatoryBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainNasaObservatoryBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewPager.adapter = ViewPagerAdapter(this@MainFragmentNasaObservatory)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.first_observatory)
                    1 -> getString(R.string.second_observatory)
                    2 -> getString(R.string.third_observatory)
                    3 -> getString(R.string.fourth_observatory)
                    else -> error("Unsupported position $position")
                }
            }.attach()
        }
    }
}