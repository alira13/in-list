package com.example.inlist.di

import com.example.inlist.currentList.domain.usecases.CurrentListInteractor
import com.example.inlist.currentList.domain.usecases.CurrentListInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindCurrentListInteractor(impl:CurrentListInteractorImpl):CurrentListInteractor
}