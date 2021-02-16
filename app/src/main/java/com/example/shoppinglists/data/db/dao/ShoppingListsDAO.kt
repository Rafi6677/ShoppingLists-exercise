package com.example.shoppinglists.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglists.data.db.model.ShoppingList

@Dao
interface ShoppingListsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingList): Long

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingList): Int

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingList): Int

    @Query("SELECT * FROM shopping_lists WHERE is_archive = 1 ORDER BY timestamp DESC")
    fun getArchivedShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_lists WHERE is_archive = 0 ORDER BY timestamp DESC")
    fun getShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_lists WHERE id LIKE :shoppingListId")
    fun getSpecificShoppingList(shoppingListId: Int): ShoppingList

}