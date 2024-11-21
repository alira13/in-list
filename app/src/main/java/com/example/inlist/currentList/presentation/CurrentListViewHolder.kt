package com.example.inlist.currentList.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inlist.R
import com.example.inlist.currentList.domain.models.ListItem

class CurrentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.list_item_tv)
    fun bind(listItem: ListItem) {
        name.text = listItem.name
    }
}