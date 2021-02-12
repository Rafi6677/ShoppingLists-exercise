package com.example.shoppinglists.domain.repository

import com.example.shoppinglists.data.model.ShoppingList
import kotlinx.coroutines.flow.Flow

interface ShoppingListsRepository {

    suspend fun addShoppingList(shoppingList: ShoppingList)
    suspend fun deleteShoppingList(shoppingList: ShoppingList)
    suspend fun editShoppingList(shoppingList: ShoppingList)
    fun getArchivedShoppingLists(): Flow<List<ShoppingList>>
    fun getShoppingLists(): Flow<List<ShoppingList>>
    fun getSpecificShoppingList(): Flow<ShoppingList>

}