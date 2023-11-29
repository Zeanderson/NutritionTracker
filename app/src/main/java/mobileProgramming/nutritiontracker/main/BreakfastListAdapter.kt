package mobileProgramming.nutritiontracker.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mobileProgramming.nutritiontracker.R
import mobileProgramming.nutritiontracker.data.Item

class BreakfastListAdapter(val itemClicked:(item:Item)->Unit): ListAdapter<Item, BreakfastListAdapter.ItemViewHolder>(ItemsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.itemName, current.itemCalories.toString())
        holder.itemView.tag= current
        holder.itemView.setOnClickListener{
            itemClicked(holder.itemView.tag as Item)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val breakfastItemTitle: TextView = itemView.findViewById(R.id.breakfastItemTitle)
        private val breakfastItemCal: TextView = itemView.findViewById(R.id.breakfastItemCal)

        fun bind(title: String?, cal: String?) {
            breakfastItemTitle.text = title
            breakfastItemCal.text = cal
        }
        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_breakfast_item, parent, false)
                return ItemViewHolder(view)
            }
        }
    }

    class ItemsComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.itemName == newItem.itemName
        }
    }
}