package com.rukka.jsonplaceholder.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rukka.jsonplaceholder.databinding.OverViewItemBinding
import com.rukka.jsonplaceholder.networks.Property

class Adapter(private val onClickListener: OnclickListener) : ListAdapter<Property, Adapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.property = item
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    class ViewHolder(val binding: OverViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OverViewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem == newItem
        }
    }

    class OnclickListener(val clickListener: (property: Property) -> Unit) {
        fun onClick(property: Property) = clickListener(property)
    }
}