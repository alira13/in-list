package com.example.inlist.allLists.domain

import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.ListItem
import javax.inject.Inject

class AllListsInteractorImpl @Inject constructor() : AllListsInteractor {
    override fun getItems(): List<CurrentList> {
        val lists = mutableListOf<CurrentList>()
        for (i in 1..100){
            lists.add(CurrentList(emptyList<ListItem>().toMutableList(), "Список $i"))
        }
        return lists
    }

    override fun addItem(list: CurrentList) {
        TODO("Not yet implemented")
    }

    override fun deleteItem(id: Int) {
        TODO("Not yet implemented")
    }

    override fun restoreItem(id: Int) {
        TODO("Not yet implemented")
    }
}