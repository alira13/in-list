package com.example.inlist.currentList.domain.usecases

class CurrentListInteractorImpl(private val repository: CurrentListRepository) :
    CurrentListInteractor {
    override fun method() {
        repository.method()
    }
}