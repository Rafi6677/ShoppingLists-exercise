package com.example.shoppinglists.data.repository.datasource

import androidx.lifecycle.LiveData
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.utils.Resource

interface ShoppingListsDataSource {

    suspend fun insertShoppingListIntoDB(shoppingList: ShoppingList)
    suspend fun deleteShoppingListFromDB(shoppingList: ShoppingList)
    suspend fun updateShoppingListToDB(shoppingList: ShoppingList)
    fun getArchivedShoppingListsFromDB(): LiveData<List<ShoppingList>>
    fun getShoppingListsFromDB(): LiveData<List<ShoppingList>>
    fun getSpecificShoppingListFromDB(id: Int): ShoppingList

}