package com.example.shoppinglists.presentation.enums

enum class ShoppingListOperationType(val value: Int) {

    Add (0),
    ShowDetails(1),
    ShowArchivedDetails(2);

    companion object {
        private val map = values().associateBy(ShoppingListOperationType::value)

        fun setByValue(value: Int) = map[value]
    }

}