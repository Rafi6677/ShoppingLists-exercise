package com.example.shoppinglists.presentation.enums

enum class ShoppingListOperationType(val value: Int) {

    Add (0),
    ShowDetails(1);

    companion object {
        private val map = values().associateBy(ShoppingListOperationType::value)

        fun setByValue(value: Int) = map[value]
    }

}