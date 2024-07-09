package com.example.inlist.currentList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inlist.currentList.presentation.models.CurrentList
import com.example.inlist.currentList.presentation.models.ListItem

class CurrentListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Имя списка"
    }
    val text: LiveData<String> = _text

    private val _currentList = MutableLiveData<CurrentList>()
    val currentList: LiveData<CurrentList> = _currentList

    init {
        getCurrentList()
        getDeletedList()
    }

    private fun getCurrentList() {
        val items = listOf(
            ListItem("Молоко"),
            ListItem("Чай"),
            ListItem("Кофе"),
            ListItem("Хлеб")
        )
        val list = CurrentList("Список 1", items)
    }

    private fun getDeletedList() {

    }
}