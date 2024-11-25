package com.example.inlist.allLists.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.inlist.R
import com.example.inlist.currentList.domain.models.CurrentList

class AllListsAdapter : ListAdapter<CurrentList, AllListsViewHolder>(AllListsDiffUtilCallback()) {
    var onClick: ((CurrentList) -> Any)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllListsViewHolder {
        val viewId = R.layout.view_list
        val viewItem = LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        return AllListsViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: AllListsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onClick?.invoke(getItem(position)) }
    }
}