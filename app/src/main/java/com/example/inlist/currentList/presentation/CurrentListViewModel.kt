package com.example.inlist.currentList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.ListItem
import com.example.inlist.currentList.domain.usecases.CurrentListInteractor
import javax.inject.Inject

class CurrentListViewModel @Inject constructor(var interactor: CurrentListInteractor) : ViewModel() {

    private val _state = MutableLiveData<CurrentList>()
    val state: LiveData<CurrentList> = _state

    private var currentList: CurrentList? = null
    init {
        interactor.method()
        getCurrentList()
        _state.postValue(currentList!!)
    }

    private fun getCurrentList(): CurrentList {
        val activeItems = listOf(
            ListItem("Молоко"),
            ListItem("Чай"),
            ListItem("Кофе"),
            ListItem("Хлеб")
        )

        val deletedItems = listOf(
            ListItem("Шампунь"),
            ListItem("Мыло"),
        )
        currentList = CurrentList("Мой список", activeItems, deletedItems)
        return currentList!!
    }

    fun addItem(name: String) {
        if (name.isNotEmpty()) {
            val item = ListItem(name)
            val items = currentList!!.activeItems.toMutableList()
            items.add(0, item)
            val list = CurrentList(currentList!!.name, items, currentList!!.deletedItems)
            currentList = list
            _state.postValue(currentList!!)
        }
    }

    fun deleteItem(name: String) {
        val item = ListItem(name)
        val activeItems = currentList!!.activeItems.toMutableList()
        val deletedItems = currentList!!.deletedItems.toMutableList()
        activeItems.remove(item)
        deletedItems.add(0, item)
        val list = CurrentList(currentList!!.name, activeItems, deletedItems)
        currentList = list
        _state.postValue(currentList!!)
    }

    fun restoreItem(name: String) {
        val item = ListItem(name)
        val activeItems = currentList!!.activeItems.toMutableList()
        val deletedItems = currentList!!.deletedItems.toMutableList()
        activeItems.add(0, item)
        deletedItems.remove(item)
        val list = CurrentList(currentList!!.name, activeItems, deletedItems)
        currentList = list
        _state.postValue(currentList!!)
    }
}