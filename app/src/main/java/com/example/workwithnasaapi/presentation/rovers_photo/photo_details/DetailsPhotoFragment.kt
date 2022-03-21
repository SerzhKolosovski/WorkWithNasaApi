package com.example.workwithnasaapi.presentation.rovers_photo.photo_details

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.workwithnasaapi.databinding.FragmentDetailsPhotoBinding
import coil.load
import com.example.workwithnasaapi.R
import kotlinx.coroutines.launch

class DetailsPhotoFragment : Fragment() {

    private var _binding: FragmentDetailsPhotoBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val notificationManager by lazy {
        NotificationManagerCompat.from(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailsPhotoBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    private fun createChannel() =
        NotificationChannelCompat.Builder(
            CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_DEFAULT
        )
            .setName("NASA Api notification")
            .setSound(null, null)
            .build()

    private val hasReadPermission: Boolean
        get() = hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)

    private val hasWritePermission: Boolean
        get() = hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    private fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionsIfNeeded() {
        val permissions = listOfNotNull(
            Manifest.permission.READ_EXTERNAL_STORAGE.takeIf { !hasReadPermission },
            Manifest.permission.WRITE_EXTERNAL_STORAGE.takeIf { !hasWritePermission }
        )
        if (permissions.isNotEmpty()) {
            requestPermissions.launch(permissions.toTypedArray())
        }
    }

    private val requestPermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true || Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        } else {
            Toast.makeText(
                requireContext(),
                "Don't have access to read external storage",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val viewModel by viewModels<DetailsViewModel>()

    private val args: DetailsPhotoFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationManager.createNotificationChannel(createChannel())
        requestPermissionsIfNeeded()

        with(binding) {
            image.load(args.image)
            id.text = getString(R.string.id_photo_details_photo)+ args.id
            sol.text = getString(R.string.sol_details_photo)+ args.sol
            nameCamera.text = getString(R.string.name_camera_details_photo)+ args.nameCamera
            landingDate.text = getString(R.string.landing_date_details_photo)+ args.landingData
            launchDate.text = getString(R.string.launchDate_details_photo)+ args.launchData
            status.text = getString(R.string.status_of_rover_details_photo)+ args.status
            buttonDownload.setOnClickListener {
                lifecycleScope.launch {
                    loadImage()
                    val notification = buildNotification()
                    notificationManager.notify(NOTIFICATION_ID, notification)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun buildNotification(): Notification {
        val builder = NotificationCompat.Builder(
            requireContext(),
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("File ${args.id} is downloaded")
        return builder.build()
    }

    private suspend fun loadImage() {
        if (hasWritePermission) {
            viewModel.saveImage(args.id, args.image)
        }
    }

    companion object {
        const val CHANNEL_ID = "Downloader"
        const val NOTIFICATION_ID = 1
    }
}