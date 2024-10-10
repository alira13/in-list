package com.example.inlist.currentList.domain.models

data class CurrentList(
    val name: String,
    val activeItems: List<ListItem>,
    val deletedItems: List<ListItem>
)