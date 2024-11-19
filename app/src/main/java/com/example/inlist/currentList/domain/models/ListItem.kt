package com.example.inlist.currentList.domain.models

data class ListItem(val name: String, var isEnabled: Boolean = true, var id: Int = UNDEFINED_ID) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}