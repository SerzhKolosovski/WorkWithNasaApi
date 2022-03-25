package com.example.workwithnasaapi.presentation.rovers_photo.list_rovers_photos

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workwithnasaapi.databinding.FragmentListPhotosBinding
import com.example.workwithnasaapi.presentation.model.State
import com.example.workwithnasaapi.presentation.rovers_photo.adapter.PhotoAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ListPhotosFragment : Fragment() {
    private var _binding: FragmentListPhotosBinding? = null
    private val binding: FragmentListPhotosBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val args: ListPhotosFragmentArgs by navArgs()

    private val viewModel by viewModel<ListPhotosViewModel>() {
        parametersOf(args.modelOfRover)
    }

    private val adapter = PhotoAdapter { photo ->
        findNavController().navigate(
            ListPhotosFragmentDirections.toDetailsPhotoFragment(
                photo.id.toString(),
                photo.imgSrc,
                photo.sol.toString(),
                photo.camera.fullName,
                photo.roverModelOf.landingDate,
                photo.roverModelOf.launchDate,
                photo.roverModelOf.status
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListPhotosBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(view.context)
        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager
            viewModel
                .photoFlow
                .onEach { state ->
                    progress.isVisible = state == State.Loading
                    when (state) {
                        is State.Content -> {
                            val photos = state.value
                            adapter.submitList(photos)
                        }
                        is State.Error -> {
                            Snackbar.make(
                                root,
                                state.throwable.localizedMessage ?: "Error",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                    recyclerView.addHorizontalSpaceDecoration(SIZE_TOP)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun RecyclerView.addHorizontalSpaceDecoration(space: Int) {
        addItemDecoration(
            object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val position = parent.getChildAdapterPosition(view)
                    if (position != 0 && position != parent.adapter?.itemCount) {
                        outRect.top = space
                    }
                }
            }
        )
    }

    companion object {
        private const val SIZE_TOP = 50
    }
}
