package by.itacademy.pvt.homework.dz6

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.itacademy.pvt.homework.R

class DZ6Adapter(private var items: List<Student>, private val listener: ClickListener) : RecyclerView.Adapter<DZ6ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DZ6ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_dz6, parent, false)

        val holder = DZ6ListViewHolder(view)

        holder.itemView.setOnClickListener { listener.onItemClickListener(items[holder.adapterPosition]) }

        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: DZ6ListViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    public fun updateList() {
        items = DZ6Singleton.instance.getStudentsList()
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClickListener(item: Student)
    }
}