package com.example.workwithnasaapi.presentation.nasa_observatory.google_map

import android.Manifest
import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.workwithnasaapi.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class MapsFragment : Fragment() {

    private val args: MapsFragmentArgs by navArgs()

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isEnabled ->
        setLocationEnabled(isEnabled)
        if (isEnabled) {
            viewLifecycleOwner.lifecycleScope.launch {
                getLocationObservatory()
            }
        }
    }

    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(getLocationObservatory())
        mapFragment.getMapAsync {
            it.apply {
                uiSettings.isZoomControlsEnabled = true
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setLocationEnabled(enabled: Boolean) {
        googleMap?.isMyLocationEnabled = enabled
        googleMap?.uiSettings?.isMyLocationButtonEnabled = enabled
    }

    private fun getLocationObservatory(): OnMapReadyCallback {
        return OnMapReadyCallback { googleMap ->
            val observatoryLocation =
                LatLng(args.coordinates[0].toDouble(), args.coordinates[1].toDouble())
            googleMap.addMarker(
                MarkerOptions().position(observatoryLocation).title(args.theNameOfTheObservatory)
                    .icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.pngwing)
                    )
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(observatoryLocation))
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(observatoryLocation, DEFAULT_CAMERA_ZOOM)
            )
            return@OnMapReadyCallback
        }
    }

    companion object {
        private const val DEFAULT_CAMERA_ZOOM = 14f
    }
}