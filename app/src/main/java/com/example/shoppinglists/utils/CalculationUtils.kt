package com.example.shoppinglists.utils

import android.annotation.SuppressLint
import com.example.shoppinglists.data.db.model.ShoppingList
import java.text.SimpleDateFormat
import java.util.*

object CalculationUtils {

    fun countBoughtProducts(shoppingList: ShoppingList): Int {
        var boughtProducts = 0

        for (product in shoppingList.productsList) {
            if (product.isBought) {
                boughtProducts++
            }
        }

        return boughtProducts
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDateFromTimestampToString(timestamp: Long): String {
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val date = Date(timestamp)

        return formatter.format(date)
    }

}