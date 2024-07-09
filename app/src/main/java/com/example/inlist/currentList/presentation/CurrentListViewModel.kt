package com.example.inlist.currentList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inlist.currentList.presentation.models.CurrentList
import com.example.inlist.currentList.presentation.models.ListItem

class CurrentListViewModel : ViewModel() {

    private val _currentList = MutableLiveData<CurrentList>()
    val currentList: LiveData<CurrentList> = _currentList

    init {
        val list = getCurrentList()
        _currentList.postValue(list)
    }

    private fun getCurrentList():CurrentList {
        val items = listOf(
            ListItem("Молоко"),
            ListItem("Чай"),
            ListItem("Кофе"),
            ListItem("Хлеб")
        )
        val list = CurrentList("Список 1", items)
        return list
    }
}