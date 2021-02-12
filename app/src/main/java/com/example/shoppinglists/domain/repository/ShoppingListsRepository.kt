package com.example.shoppinglists.domain.repository

import com.example.shoppinglists.data.db.model.ShoppingList

interface ShoppingListsRepository {

    suspend fun addShoppingList(shoppingList: ShoppingList)
    suspend fun deleteShoppingList(shoppingList: ShoppingList)
    suspend fun editShoppingList(shoppingList: ShoppingList)
    suspend fun getArchivedShoppingLists(): List<ShoppingList>
    suspend fun getShoppingLists(): List<ShoppingList>
    suspend fun getSpecificShoppingList(id: Int): ShoppingList

}