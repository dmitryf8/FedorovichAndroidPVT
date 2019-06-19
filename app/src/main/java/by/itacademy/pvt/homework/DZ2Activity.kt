package by.itacademy.pvt.homework

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_dz2.*

class DZ2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)
        urlEditText.setText(R.string.imageURL)
        loadButton.setOnClickListener { kotlin.run {
            loadImage()
            }
        }
    }

    fun loadImage() {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide
            .with(imageView)
            .load(urlEditText.text.toString())
            .centerInside()
            .placeholder(circularProgressDrawable)
            .into(imageView)
    }
}
