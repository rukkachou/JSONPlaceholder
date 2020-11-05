package com.rukka.jsonplaceholder

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

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