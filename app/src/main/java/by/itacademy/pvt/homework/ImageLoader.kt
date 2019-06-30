package by.itacademy.pvt.homework

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.regex.Pattern

fun load(imageView: ImageView, url: String) {
    if (isURL(url)) {
        Glide
            .with(imageView)
            .load(url)
            .centerInside()
            .into(imageView)
    }
}

fun isURL(url: String): Boolean {
    val pattern = Pattern.compile("^http|^https|^www")
    val matcher = pattern.matcher(url)
    return matcher.find()
}
