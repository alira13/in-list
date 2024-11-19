package com.example.inlist.currentList.domain.usecases

import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.ListItem

interface CurrentListRepository {
    fun getItems(): CurrentList
    fun deleteItem(id:Int)
    fun addItem(item: ListItem)
    fun restoreItem(id:Int)
}