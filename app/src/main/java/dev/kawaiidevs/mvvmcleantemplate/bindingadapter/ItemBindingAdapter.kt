package dev.kawaiidevs.mvvmcleantemplate.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import dev.kawaiidevs.mvvmcleantemplate.utils.ImageLoader

@BindingAdapter("loadImageUrl")
fun ImageView.loadImage(url: String?) {
    url?.let { ImageLoader(context).loadImage(url, this) }
}