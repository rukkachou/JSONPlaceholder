package com.rukka.jsonplaceholder

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.rukka.jsonplaceholder.overview.Status

@BindingAdapter("apiStatus")
fun bindStatusWithImage(imageView: ImageView, status: Status) {
    when (status) {
        Status.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
        Status.DONE -> {
            imageView.visibility = View.INVISIBLE
        }
        Status.FAILED -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imageView.context)
            .load(
                GlideUrl(
                    imgUrl, LazyHeaders
                        .Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build()
                )
            )
            .centerCrop()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}