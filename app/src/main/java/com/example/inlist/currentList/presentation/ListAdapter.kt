package com.example.inlist.currentList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.inlist.R
import com.example.inlist.currentList.presentation.models.ListItem

class ListViewHolder(itemView: View) : ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.list_item_tv)
    fun bind(listItem: ListItem) {
        name.text = listItem.name
    }
}

class ListAdapter() : Adapter<ListViewHolder>() {
    var listItems: MutableList<ListItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.count()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listItems[position])
    }
}