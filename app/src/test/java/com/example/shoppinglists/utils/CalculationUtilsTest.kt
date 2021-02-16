package com.example.shoppinglists.utils

import com.example.shoppinglists.data.db.model.Product
import org.junit.Assert.*
import org.junit.Test

class CalculationUtilsTest {

    @Test
    fun countBoughtProducts_productsGiven_returnsCorrectNumberOfBoughtProducts() {
        val products = ArrayList<Product>().apply {
            add(Product("apple", 1, true))
            add(Product("orange", 1, false))
            add(Product("banana", 1, true))
        }
        val result = CalculationUtils.countBoughtProducts(products)

        assertEquals(result, 2)
    }

    @Test
    fun formatDateFromTimestampToString_timestampGiven_returnsCorrectResult() {
        val result = CalculationUtils.formatDateFromTimestampToString(1613502000000)
        assertEquals(result, "16-02-2021 20:00")
    }

}