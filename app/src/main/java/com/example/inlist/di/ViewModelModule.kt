package com.example.inlist.di

import androidx.lifecycle.ViewModel
import com.example.inlist.allLists.presentation.AllListsViewModel
import com.example.inlist.currentList.presentation.CurrentListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrentListViewModel::class)
    fun bindCurrentListViewModel(impl:CurrentListViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllListsViewModel::class)
    fun bindAllListsViewModel(impl: AllListsViewModel):ViewModel
}