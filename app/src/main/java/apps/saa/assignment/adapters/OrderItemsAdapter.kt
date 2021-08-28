package apps.saa.assignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import apps.saa.assignment.databinding.ChildOrderItemBinding
import apps.saa.networking.model.Order

interface SetOnItemClickListener {
    fun onItemClicked(order: Order)
}

class OrderItemsAdapter(val clickListener: SetOnItemClickListener): ListAdapter<Order, OrderItemsAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ChildOrderItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.apply {
                orderId.text = "# " + order.id
                name.text = order.name
                address.text = order.address
                parent.setOnClickListener {
                    clickListener.onItemClicked(order)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ChildOrderItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    private class DiffCallback: DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.equals(newItem)
        }
    }
}