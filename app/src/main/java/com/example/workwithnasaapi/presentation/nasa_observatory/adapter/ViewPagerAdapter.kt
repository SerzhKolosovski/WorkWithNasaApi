package com.example.workwithnasaapi.presentation.nasa_observatory.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.workwithnasaapi.presentation.nasa_observatory.observatories.FirstObservatory
import com.example.workwithnasaapi.presentation.nasa_observatory.observatories.FourObservatory
import com.example.workwithnasaapi.presentation.nasa_observatory.observatories.SecondObservatory
import com.example.workwithnasaapi.presentation.nasa_observatory.observatories.ThreeObservatory

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstObservatory()
            1 -> SecondObservatory()
            2 -> ThreeObservatory()
            3 -> FourObservatory()
            else -> error("Unsupported position $position")
        }
    }
}
