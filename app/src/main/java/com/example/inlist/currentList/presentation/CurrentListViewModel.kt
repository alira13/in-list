package com.example.inlist.currentList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.ListItem
import com.example.inlist.currentList.domain.usecases.CurrentListInteractor
import javax.inject.Inject

class CurrentListViewModel @Inject constructor(var interactor: CurrentListInteractor) :
    ViewModel() {

    private val _state = MutableLiveData<CurrentList>()
    val state: LiveData<CurrentList> = _state

    init {
        getItems()
    }

    private fun getItems() {
        val currentList = interactor.getItems()
        _state.postValue(currentList)
    }

    fun addItem(inputName: String) {
        val name = parseName(inputName)
        val inputResult = validateInput(name)
        if (inputResult) {
            interactor.addItem(ListItem(name))
            getItems()
        }
    }

    fun deleteItem(id: Int) {
        interactor.deleteItem(id)
        getItems()
    }

    fun restoreItem(id: Int) {
        interactor.restoreItem(id)
        getItems()
    }

    private fun parseName(inputName: String): String {
        return inputName.trim()
    }

    private fun validateInput(inputName: String): Boolean {
        var result = true

        if (inputName.isEmpty() )
            result = false
        if (_state.value?.items?.any { it.name == inputName } == true)
            result = false
        return result
    }
}