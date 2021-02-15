package com.example.shoppinglists.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.utils.Resource

interface ShoppingListsRepository {

    suspend fun addShoppingList(shoppingList: ShoppingList)
    suspend fun deleteShoppingList(shoppingList: ShoppingList)
    suspend fun editShoppingList(shoppingList: ShoppingList)
    fun getArchivedShoppingLists(): LiveData<List<ShoppingList>>
    fun getShoppingLists(): LiveData<List<ShoppingList>>
    fun getSpecificShoppingList(id: Int): ShoppingList

}