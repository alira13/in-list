package com.example.inlist.allLists.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inlist.allLists.domain.AllListsInteractor
import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.ListItem
import javax.inject.Inject

class AllListsViewModel @Inject constructor(var interactor: AllListsInteractor) : ViewModel() {

    private val _state = MutableLiveData<List<CurrentList>>()
    val state: LiveData<List<CurrentList>> = _state

    init {
        getItems()
    }

    private fun getItems() {
        val items = interactor.getItems()
        _state.postValue(items!!)
    }

    fun addItem(name: String) {
        interactor.addItem(CurrentList(emptyList<ListItem>().toMutableList(), name))
        getItems()
    }

    fun deleteItem(id: Int) {
        interactor.deleteItem(id)
        getItems()
    }

    fun restoreItem(id: Int) {
        interactor.restoreItem(id)
        getItems()
    }
}