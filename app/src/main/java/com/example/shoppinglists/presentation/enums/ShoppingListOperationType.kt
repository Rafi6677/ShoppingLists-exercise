package com.example.shoppinglists.presentation.enums

enum class ShoppingListOperationType(val value: Int) {

    Add (0),
    Edit(1),
    ShowDetails(2),
    ShowArchivedDetails(3);

    companion object {
        private val map = values().associateBy(ShoppingListOperationType::value)

        fun setByValue(value: Int) = map[value]
    }

}