package by.itacademy.pvt.homework.dz8

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.itacademy.pvt.homework.R

class DZ8Adapter(private var items: List<Student>, private val listener: ClickListener) : RecyclerView.Adapter<DZ8ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DZ8ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_dz6, parent, false)

        val holder = DZ8ListViewHolder(view)

        holder.itemView.setOnClickListener { listener.onItemClicked(items[holder.adapterPosition]) }

        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: DZ8ListViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun showList(list: ArrayList<Student>) {
        items = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClicked(item: Student)
    }
}