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

class SnackListAdapter(val itemClicked:(item:Item)->Unit): ListAdapter<Item, SnackListAdapter.ItemViewHolder>(ItemsComparator()) {

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
        private val snackItemTitle: TextView = itemView.findViewById(R.id.snackItemTitle)
        private val snackItemCal: TextView = itemView.findViewById(R.id.snackItemCal)

        fun bind(title: String?, cal: String?) {
            snackItemTitle.text = title
            snackItemCal.text = cal
        }
        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_snack_item, parent, false)
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