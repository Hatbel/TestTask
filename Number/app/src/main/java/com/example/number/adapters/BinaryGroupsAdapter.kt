package com.example.number.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.number.databinding.BinaryGroupLayotBinding
import com.example.number.interfaces.ClickListener
import com.example.number.model.BinaryNumberGroup

class BinaryGroupsAdapter(
    diffCallback: DiffUtil.ItemCallback<BinaryNumberGroup>,
    private val cellClickListener: ClickListener
) :
    PagingDataAdapter<BinaryNumberGroup, BinaryGroupsAdapter.DataViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        return DataViewHolder(
            BinaryGroupLayotBinding.inflate(LayoutInflater.from(parent.context)),
            cellClickListener
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DataViewHolder(
        private val binding: BinaryGroupLayotBinding,
        private val cellClickListener: ClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(binaryGroup: BinaryNumberGroup?) {
            binding.groupNumberTextView.text = binaryGroup?.groupId.toString()
            binding.bingroupItemView.setOnClickListener {
                if (binaryGroup != null) {
                    cellClickListener.onCellClickListener(binaryGroup.groupId)
                }
            }
        }
    }

}

object BinaryNumberGroupComparator : DiffUtil.ItemCallback<BinaryNumberGroup>() {
    override fun areItemsTheSame(oldItem: BinaryNumberGroup, newItem: BinaryNumberGroup): Boolean {
        // Id is unique.
        return oldItem.groupId == newItem.groupId
    }

    override fun areContentsTheSame(
        oldItem: BinaryNumberGroup,
        newItem: BinaryNumberGroup
    ): Boolean {
        return oldItem == newItem
    }
}
