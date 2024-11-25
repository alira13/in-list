package com.example.inlist.allLists.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inlist.R
import com.example.inlist.currentList.domain.models.CurrentList

class AllListsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textView:TextView = itemView.findViewById(R.id.list_tv)
    fun bind(currentList:CurrentList){
        textView.text = currentList.name
    }
}