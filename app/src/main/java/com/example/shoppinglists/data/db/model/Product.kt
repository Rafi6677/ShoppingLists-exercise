package com.example.shoppinglists.data.db.model

import java.io.Serializable

data class Product(
    val name: String,
    val quantity: Int,
    var isBought: Boolean
): Serializable