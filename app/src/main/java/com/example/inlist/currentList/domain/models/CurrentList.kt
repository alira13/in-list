package com.example.inlist.currentList.domain.models

data class CurrentList(
    var items: MutableList<ListItem> = mutableListOf(),
    val name: String = UNDEFINED_NAME,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = -1
        const val UNDEFINED_NAME = "Список"
    }
}
