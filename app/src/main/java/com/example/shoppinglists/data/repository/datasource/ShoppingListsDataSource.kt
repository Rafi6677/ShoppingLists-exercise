package com.example.shoppinglists.data.repository.datasource

import com.example.shoppinglists.data.db.model.ShoppingList

interface ShoppingListsDataSource {

    suspend fun insertShoppingListIntoDB(shoppingList: ShoppingList)
    suspend fun deleteShoppingListFromDB(shoppingList: ShoppingList)
    suspend fun updateShoppingListToDB(shoppingList: ShoppingList)
    suspend fun getArchivedShoppingListsFromDB(): List<ShoppingList>
    suspend fun getShoppingListsFromDB(): List<ShoppingList>
    suspend fun getSpecificShoppingListFromDB(id: Int): ShoppingList

}