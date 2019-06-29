package by.itacademy.pvt.homework.dz5

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.homework.R
import kotlinx.android.synthetic.main.activity_dz5.*

class DZ5Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5)

        val diagramData = ArrayList<Item>()
        var item: Item
        for (i in 1..5) {
            item = Item(i, i.toString())
            diagramData.add(item)
        }
        diagramDZ5.setList(diagramData)
    }
}
