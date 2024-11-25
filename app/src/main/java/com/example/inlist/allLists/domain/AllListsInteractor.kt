package com.example.inlist.allLists.domain

import com.example.inlist.currentList.domain.models.CurrentList

interface AllListsInteractor {
    fun getItems(): List<CurrentList>
    fun addItem(list: CurrentList)
    fun deleteItem(id: Int)
    fun restoreItem(id: Int)
}