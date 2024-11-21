package com.example.inlist.currentList.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.inlist.currentList.domain.models.ListItem

class CurrentListDiffUtilCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}