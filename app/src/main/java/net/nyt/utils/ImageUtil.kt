package net.nyt.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import net.nyt.nytsample.R

class ImageUtil {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl", "error"], requireAll = false)
        fun loadImage(imageView: ImageView, imageUrl: String?, error: Int) {
            Glide.with(imageView.context).load(imageUrl).placeholder(R.drawable.ic_nytimes_ar21)
                .into(imageView)
        }
    }
}