package com.example.number.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.number.R
import com.example.number.model.ShopEntity

class ShopAdapter(diffCallback: ShopComparator) :
    PagingDataAdapter<ShopEntity, ShopAdapter.DataViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shop_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.name_text_view)
        private val itemPrice: TextView = itemView.findViewById(R.id.price_text_view)
        fun bind(shopEntity: ShopEntity?) {
            itemName.text = shopEntity?.name
            itemPrice.text = shopEntity?.value.toString()
        }

    }
    object ShopComparator : DiffUtil.ItemCallback<ShopEntity>() {
        override fun areItemsTheSame(oldItem: ShopEntity, newItem: ShopEntity): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopEntity, newItem: ShopEntity): Boolean {
            return oldItem == newItem
        }
    }

}