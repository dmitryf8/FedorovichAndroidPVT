package by.itacademy.pvt.homework.dz9

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import by.itacademy.pvt.homework.R
import by.itacademy.pvt.myapplication.dz9.entity.Poi

class DZ9ListViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    val fleetTypeTextView = view.findViewById<TextView>(R.id.fleetTypeTextViewDZ9)
    val latTextViewDZ9 = view.findViewById<TextView>(R.id.latitudeTextViewDZ9)
    val lonTextViewDZ9 = view.findViewById<TextView>(R.id.longitudeTextViewDZ9)

    fun bind(poi: Poi) {
        fleetTypeTextView.setText(poi.fleetType?.toString())
        val coord = poi.coordinate
        latTextViewDZ9.setText(coord?.latitude.toString())
        lonTextViewDZ9.setText(coord?.longitude.toString())
    }
}