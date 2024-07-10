package com.example.inlist.currentList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.ListItem

class CurrentListViewModel() : ViewModel() {

    private val _state = MutableLiveData<CurrentList>()
    val state: LiveData<CurrentList> = _state

    private var currentList: CurrentList? = null
    init {
        getCurrentList()
        _state.postValue(currentList!!)
    }

    private fun getCurrentList(): CurrentList {
        val items = listOf(
            ListItem("Молоко"),
            ListItem("Чай"),
            ListItem("Кофе"),
            ListItem("Хлеб")
        )
        currentList = CurrentList("Список 1", items)
        return currentList!!
    }

    fun addToList(item: ListItem) {
        val items = currentList!!.activeItems.toMutableList()
        items.add(item)
        val list = CurrentList(currentList!!.name, items)
        currentList = list
        _state.postValue(currentList!!)
    }

    fun deleteFromList(item: ListItem) {
        val items = currentList!!.activeItems.toMutableList()
        items.remove(item)
        val list = CurrentList(currentList!!.name, items)
        currentList = list
        _state.postValue(currentList!!)
    }
}