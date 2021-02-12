package com.example.shoppinglists.data.db.dao

import androidx.room.*
import com.example.shoppinglists.data.db.model.ShoppingList

@Dao
interface ShoppingListsDAO {

    @Insert
    suspend fun insertShoppingList(shoppingList: ShoppingList): Long

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingList): Int

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingList): Int

    @Query("SELECT * FROM shopping_lists WHERE is_archive = 1 ORDER BY timestamp DESC")
    suspend fun getArchivedShoppingLists(): List<ShoppingList>

    @Query("SELECT * FROM shopping_lists ORDER BY timestamp DESC")
    suspend fun getShoppingLists(): List<ShoppingList>

    @Query("SELECT * FROM shopping_lists WHERE id LIKE :shoppingListId")
    suspend fun getSpecificShoppingList(shoppingListId: Int): ShoppingList

}