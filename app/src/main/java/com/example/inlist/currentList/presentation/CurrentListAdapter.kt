package com.example.inlist.currentList.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.inlist.R
import com.example.inlist.currentList.domain.models.ListItem


class CurrentListAdapter :
    ListAdapter<ListItem, CurrentListViewHolder>(CurrentListDiffUtilCallback()) {

    var onClick: ((ListItem) -> Any)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentListViewHolder {
        val viewId = when (viewType) {
            ACTIVE_LIST_ITEM -> R.layout.view_list_item
            DELETED_ITEM_VIEW -> R.layout.view_deleted_item
            else -> R.layout.view_list_item
        }

        val view = LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        return CurrentListViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isEnabled) ACTIVE_LIST_ITEM
        else DELETED_ITEM_VIEW
    }

    override fun onBindViewHolder(holder: CurrentListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClick?.invoke(getItem(position))
        }
    }

    companion object {
        const val DELETED_ITEM_VIEW = 0
        const val ACTIVE_LIST_ITEM = 1
    }
}