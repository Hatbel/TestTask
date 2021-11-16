package com.example.number.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.number.R
import com.example.number.databinding.ShopItemLayoutBinding
import com.example.number.interfaces.ClickListener
import com.example.number.model.ShopEntity

class ShopAdapter(
    private var shopEntities: List<ShopEntity>,
    private val cellClickListener: ClickListener
) :
    RecyclerView.Adapter<ShopAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        return DataViewHolder(
            ShopItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(shopEntities[position])
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(shopEntities[position].id)
        }
    }

    override fun getItemCount(): Int = shopEntities.size

    class DataViewHolder(
        private val binding: ShopItemLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shopEntity: ShopEntity?) {
            binding.nameTextView.text = shopEntity?.name
            binding.priceTextView.text = shopEntity?.value.toString()
            if (shopEntity?.isActive == true) {
                binding.ispurchasedImage.setImageResource(R.drawable.ic_check)
            } else {
                binding.ispurchasedImage.setImageResource(R.drawable.ic_circle_small)
            }
            if (shopEntity?.isPurchased == true) binding.priceTextView.visibility =
                View.INVISIBLE else binding.priceTextView.visibility =
                View.VISIBLE
        }
    }

    fun addBooks(shopEntities: MutableList<ShopEntity>) {
        this.shopEntities = shopEntities
        notifyDataSetChanged()
    }
}

