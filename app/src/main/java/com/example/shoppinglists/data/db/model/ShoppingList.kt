package com.example.shoppinglists.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shoppinglists.utils.DataConverter
import java.io.Serializable

@Entity(tableName = "shopping_lists")
data class ShoppingList (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "products")
    val productsList: List<Product>,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
    @ColumnInfo(name = "is_archive")
    val isArchive: Boolean
): Serializable