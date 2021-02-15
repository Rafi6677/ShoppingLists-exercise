package com.example.shoppinglists.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppinglists.data.db.dao.ShoppingListsDAO
import com.example.shoppinglists.data.db.model.Product
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.utils.DataConverter

@Database(
    entities = [ShoppingList::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class ShoppingListsDatabase : RoomDatabase() {

    abstract fun ShoppingListsDAO(): ShoppingListsDAO

}