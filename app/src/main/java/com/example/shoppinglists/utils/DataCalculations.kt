package com.example.shoppinglists.utils

import com.example.shoppinglists.data.db.model.ShoppingList

object DataCalculations {

    fun countBoughtProducts(shoppingList: ShoppingList): Int {
        var boughtProducts = 0

        for (product in shoppingList.productsList) {
            if (product.isBought) {
                boughtProducts++
            }
        }

        return boughtProducts
    }

}