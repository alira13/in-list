package com.example.inlist.currentList.data.repository

import com.example.inlist.currentList.data.datasource.LocalDataSource
import com.example.inlist.currentList.data.datasource.RemoteDataSource
import com.example.inlist.currentList.data.mapper.ItemsMapper
import com.example.inlist.currentList.domain.usecases.CurrentListRepository
import javax.inject.Inject

class CurrentListRepositoryImpl @Inject constructor(
    val mapper: ItemsMapper,
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) : CurrentListRepository {
    override fun method() {
        mapper.method()
        localDataSource.method()
        remoteDataSource.method()
    }
}