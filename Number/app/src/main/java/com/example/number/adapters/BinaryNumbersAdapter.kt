package com.example.number.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.number.R
import com.example.number.databinding.BinaryNumberItemLayoutBinding
import com.example.number.interfaces.ClickListener
import com.example.number.model.BinaryNumberDB

class BinaryNumbersAdapter(
    diffCallback: DiffUtil.ItemCallback<BinaryNumberDB>,
    private val cellClickListener: ClickListener
) :
    PagingDataAdapter<BinaryNumberDB, BinaryNumbersAdapter.DataViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        return DataViewHolder(
            BinaryNumberItemLayoutBinding.inflate(LayoutInflater.from(parent.context)),
            cellClickListener
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DataViewHolder(
        private val binding: BinaryNumberItemLayoutBinding,
        private val cellClickListener: ClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(binaryNumber: BinaryNumberDB?) {
            binding.numberTextView.text = binaryNumber?.binaryNumber.toString()
            if(binaryNumber?.isFound == true){
                binding.isFoundImage.setImageResource(R.mipmap.ic_launcher)
            } else {
                binding.isFoundImage.setImageResource(R.mipmap.anime_back_round)
            }
            binding.binaryNumbersItemView.setOnClickListener {
                if (binaryNumber != null) {
                    cellClickListener.onCellClickListener(binaryNumber.groupId)
                }
            }
        }
    }

}

object BinaryNumberComparator : DiffUtil.ItemCallback<BinaryNumberDB>() {
    override fun areItemsTheSame(oldItem: BinaryNumberDB, newItem: BinaryNumberDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BinaryNumberDB,
        newItem: BinaryNumberDB
    ): Boolean {
        return oldItem == newItem
    }
}
