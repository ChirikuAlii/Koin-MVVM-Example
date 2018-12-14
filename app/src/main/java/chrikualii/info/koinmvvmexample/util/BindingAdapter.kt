package chrikualii.info.koinmvvmexample.util

import android.databinding.BindingAdapter
import android.widget.ImageView

/**
 * Created by chirikualii on 13/12/18
 * github.com/chirikualii
 */

@BindingAdapter(value = ["loadWithGlide", "prefix"], requireAll = false)
fun setImageGlide(image: ImageView, url: String, prefix: String) {
    val path = prefix + url
    image.loadImage(image.context, path)
}
