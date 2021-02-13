package com.example.shoppinglists.data.repository.datasourceimpl

import com.example.shoppinglists.data.db.dao.ShoppingListsDAO
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.data.repository.datasource.ShoppingListsDataSource
import com.example.shoppinglists.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListsDataSourceImpl(private val dao: ShoppingListsDAO) : ShoppingListsDataSource {

    override suspend fun insertShoppingListIntoDB(shoppingList: ShoppingList) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertShoppingList(shoppingList)
        }
    }

    override suspend fun deleteShoppingListFromDB(shoppingList: ShoppingList) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteShoppingList(shoppingList)
        }
    }

    override suspend fun updateShoppingListToDB(shoppingList: ShoppingList) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateShoppingList(shoppingList)
        }
    }

    override suspend fun getArchivedShoppingListsFromDB(): List<ShoppingList> = dao.getArchivedShoppingLists()
    override suspend fun getShoppingListsFromDB(): List<ShoppingList> = dao.getShoppingLists()
    override suspend fun getSpecificShoppingListFromDB(id: Int): ShoppingList = dao.getSpecificShoppingList(id)

}