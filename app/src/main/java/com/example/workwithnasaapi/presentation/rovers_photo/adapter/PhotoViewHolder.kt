package com.example.workwithnasaapi.presentation.rovers_photo.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver

import com.example.workwithnasaapi.R
import com.example.workwithnasaapi.databinding.ItemPhotoBinding
import com.example.domain.model.Photo

class PhotoViewHolder(
    private val binding: ItemPhotoBinding,
    private val onPhotoClicked: (Photo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        with(binding) {
            image.load(photo.imgSrc) {
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            val idPhoto = photo.id
            photoId.text = root.context.getString(R.string.photo_id_item_photo, idPhoto)

            val dataPhoto = photo.earthDate
            photoData.setText(R.string.photo_data_item_photo)
            data.text = dataPhoto
            root.setOnClickListener {
                onPhotoClicked(photo)
            }
        }
    }
}