package by.itacademy.pvt.homework.dz5

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.homework.R
import kotlinx.android.synthetic.main.activity_dz5.*

class DZ5Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5)

        // заполняем список
        val diagramData = ArrayList<Item>()
        var item: Item
        for (i in 1..5) {
            item = Item(i, i.toString())
            diagramData.add(item)
        }

        // передаем данные в диаграмму
        diagramDZ5.setList(diagramData)

        // анимация
        var animationImageView = findViewById<ImageView>(R.id.animationImageViewDZ5)
        var animationDrawable: AnimationDrawable = animationImageView.background as AnimationDrawable

        animationDrawable.start()
    }
}
