package com.example.inlist.currentList.domain.usecases

import javax.inject.Inject

class CurrentListInteractorImpl @Inject constructor(private val repository: CurrentListRepository) :
    CurrentListInteractor {
    override fun method() {
        repository.method()
    }
}