package com.example.inlist.di

import com.example.inlist.currentList.data.datasource.LocalDataSource
import com.example.inlist.currentList.data.datasource.LocalDataSourceImpl
import com.example.inlist.currentList.data.datasource.RemoteDataSource
import com.example.inlist.currentList.data.datasource.RemoteDataSourceImpl
import com.example.inlist.currentList.data.repository.CurrentListRepositoryImpl
import com.example.inlist.currentList.domain.usecases.CurrentListRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindLocalDataSource(impl: LocalDataSourceImpl):LocalDataSource

    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun bindCurrentListRepository(impl: CurrentListRepositoryImpl): CurrentListRepository
}