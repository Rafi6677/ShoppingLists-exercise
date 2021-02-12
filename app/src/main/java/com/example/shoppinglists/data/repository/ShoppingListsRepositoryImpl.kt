package com.example.shoppinglists.data.repository

import android.util.Log
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.data.repository.datasource.ShoppingListsDataSource
import com.example.shoppinglists.domain.repository.ShoppingListsRepository
import java.lang.Exception

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

    override suspend fun getArchivedShoppingLists(): List<ShoppingList> {
        lateinit var archivedShoppingLists: List<ShoppingList>

        try {
            archivedShoppingLists = dataSource.getArchivedShoppingListsFromDB()
        } catch (e: Exception) {
            Log.i("ExceptionTag", e.message.toString())
        }

        return archivedShoppingLists
    }

    override suspend fun getShoppingLists(): List<ShoppingList> {
        lateinit var shoppingLists: List<ShoppingList>

        try {
            shoppingLists = dataSource.getShoppingListsFromDB()
        } catch (e: Exception) {
            Log.i("ExceptionTag", e.message.toString())
        }

        return shoppingLists
    }

    override suspend fun getSpecificShoppingList(id: Int): ShoppingList {
        lateinit var shoppingList: ShoppingList

        try {
            shoppingList = dataSource.getSpecificShoppingListFromDB(id)
        } catch (e: Exception) {
            Log.i("ExceptionTag", e.message.toString())
        }

        return shoppingList
    }

}