package com.example.inlist.allLists.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.inlist.currentList.domain.models.CurrentList

class AllListsDiffUtilCallback : DiffUtil.ItemCallback<CurrentList>() {
    override fun areItemsTheSame(oldItem: CurrentList, newItem: CurrentList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrentList, newItem: CurrentList): Boolean {
        return oldItem == newItem
    }
}