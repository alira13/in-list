package com.example.inlist.currentList.data.datasource

import com.example.inlist.currentList.data.network.ApiService

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override fun method() {
        apiService.method()
    }
}