package com.example.inlist.currentList.domain.usecases

import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.CurrentList.Companion.UNDEFINED_NAME
import com.example.inlist.currentList.domain.models.ListItem
import javax.inject.Inject

class CurrentListInteractorImpl @Inject constructor(private val repository: CurrentListRepository) :
    CurrentListInteractor {
    override fun getItems(): CurrentList {
        return repository.getItems()
    }

    override fun deleteItem(id: Int) {
        repository.deleteItem(id)
    }

    override fun addItem(item: ListItem) {
        if (item.name == UNDEFINED_NAME) return
        val list = getItems()
        val existedItem = list.items.find { it.name == item.name }
        if (existedItem == null)
            repository.addItem(item)
        else
            restoreItem(existedItem.id)
    }

    override fun restoreItem(id: Int) {
        repository.restoreItem(id)
    }
}