package com.example.inlist.currentList.data.datasource

import com.example.inlist.currentList.data.network.ApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : RemoteDataSource {
    override fun method() {
        apiService.method()
    }
}