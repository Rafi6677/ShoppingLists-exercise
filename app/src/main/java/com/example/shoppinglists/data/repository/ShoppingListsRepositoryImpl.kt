package com.example.shoppinglists.data.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.data.repository.datasource.ShoppingListsDataSource
import com.example.shoppinglists.domain.repository.ShoppingListsRepository

class ShoppingListsRepositoryImpl(
    private val dataSource: ShoppingListsDataSource
) : ShoppingListsRepository {

    override suspend fun addShoppingList(shoppingList: ShoppingList) {
        dataSource.insertShoppingListIntoDB(shoppingList)
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        dataSource.deleteShoppingListFromDB(shoppingList)
    }

    override suspend fun editShoppingList(shoppingList: ShoppingList) {
        dataSource.updateShoppingListToDB(shoppingList)
    }

    override fun getArchivedShoppingLists(): LiveData<List<ShoppingList>> = dataSource.getArchivedShoppingListsFromDB()
    override fun getShoppingLists(): LiveData<List<ShoppingList>> = dataSource.getShoppingListsFromDB()

}