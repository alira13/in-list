package com.example.inlist.currentList.data.datasource

import com.example.inlist.currentList.data.database.ItemsDatabase

class LocalDataSourceImpl(val database: ItemsDatabase) : LocalDataSource {
    override fun method() {
        database.method()
    }
}