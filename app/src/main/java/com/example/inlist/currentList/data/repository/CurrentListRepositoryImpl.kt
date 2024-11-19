package com.example.inlist.currentList.data.repository

import com.example.inlist.currentList.data.datasource.LocalDataSource
import com.example.inlist.currentList.data.datasource.RemoteDataSource
import com.example.inlist.currentList.data.mapper.ItemsMapper
import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.domain.models.ListItem
import com.example.inlist.currentList.domain.models.ListItem.Companion.UNDEFINED_ID
import com.example.inlist.currentList.domain.usecases.CurrentListRepository
import javax.inject.Inject

class CurrentListRepositoryImpl @Inject constructor(
    val mapper: ItemsMapper,
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) : CurrentListRepository {

    private var currentList: CurrentList
    private var itemId = 0;

    init {
        val items = mutableListOf(
            ListItem("Молоко", true, itemId++),
            ListItem("Чай",true, itemId++),
            ListItem("Кофе",true, itemId++),
            ListItem("Хлеб",true, itemId++),
            ListItem("Шампунь", false,itemId++),
            ListItem("Мыло", false,itemId++),
        )
        currentList = CurrentList(items, "Мой список")
    }

    override fun getItems(): CurrentList {
        mapper.method()
        localDataSource.method()
        remoteDataSource.method()
        return currentList
    }

    override fun deleteItem(id: Int) {
        currentList.items.find { item -> item.id == id }!!.isEnabled = false
    }

    override fun addItem(item: ListItem) {
        if (item.id == UNDEFINED_ID) item.id = itemId++
        currentList.items.add(item)
    }

    override fun restoreItem(id: Int) {
        currentList.items.find { item -> item.id == id }!!.isEnabled = true
    }
}