package by.itacademy.pvt.homework

import android.app.Activity
import android.graphics.Color

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dz0.*

class DZ0Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz0)

        tv1.setOnClickListener { makeChange() }
        tv2.setOnClickListener { makeChange() }
        btn.setOnClickListener { makeChange() }
        btn.setTextColor(Color.RED)





    }

    private fun makeChange(){
        val s=tv1.text
        tv1.text = tv2.text
        tv2.text=s

        val i=Math.random()*4
        val j = i.toInt()

        val colorWhite= resources.getColor(R.color.colorWhite)
        val colorRed= resources.getColor(R.color.colorRed)
        val colorGreen= resources.getColor(R.color.colorGreen)
        val colorBlue= resources.getColor(R.color.colorBlue)

        when(j){
            0 -> {
                tv1.setTextColor(colorWhite)
                tv2.setTextColor(colorRed)
            }
            1 -> {
                tv1.setTextColor(colorGreen)
                tv2.setTextColor(colorBlue)
            }
            2 -> {
                tv1.setTextColor(colorRed)
                tv2.setTextColor(colorGreen)
            }
            else -> {
                tv1.setTextColor(colorBlue)
                tv2.setTextColor(colorWhite)
            }
        }



    }
}
