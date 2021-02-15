package com.example.shoppinglists.data.repository.datasourceimpl

import androidx.lifecycle.LiveData
import com.example.shoppinglists.data.db.dao.ShoppingListsDAO
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.data.repository.datasource.ShoppingListsDataSource
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

    override fun getArchivedShoppingListsFromDB(): LiveData<List<ShoppingList>> = dao.getArchivedShoppingLists()
    override fun getShoppingListsFromDB(): LiveData<List<ShoppingList>> = dao.getShoppingLists()
    override fun getSpecificShoppingListFromDB(id: Int): ShoppingList = dao.getSpecificShoppingList(id)

}