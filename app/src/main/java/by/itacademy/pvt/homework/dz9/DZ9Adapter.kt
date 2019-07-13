package by.itacademy.pvt.homework.dz9

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.itacademy.pvt.homework.R
import by.itacademy.pvt.myapplication.dz9.entity.Poi

class DZ9Adapter(private var items: List<Poi>, private val listener: ClickListenerDZ9) : androidx.recyclerview.widget.RecyclerView.Adapter<DZ9ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DZ9ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_dz9, parent, false)

        val holder = DZ9ListViewHolder(view)

        holder.itemView.setOnClickListener { listener.onItemClicked(items[holder.adapterPosition]) }

        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: DZ9ListViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun showList(list: List<Poi>) {
        items = list
        notifyDataSetChanged()
    }

    interface ClickListenerDZ9 {
        fun onItemClicked(item: Poi)
    }
}