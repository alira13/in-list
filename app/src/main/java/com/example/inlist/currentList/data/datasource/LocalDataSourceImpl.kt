package com.example.inlist.currentList.data.datasource

import com.example.inlist.currentList.data.database.ItemsDatabase
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(val database: ItemsDatabase) : LocalDataSource {
    override fun method() {
        database.method()
    }
}