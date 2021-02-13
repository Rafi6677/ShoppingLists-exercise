package com.example.shoppinglists.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.shoppinglists.data.db.ShoppingListsDatabase
import com.example.shoppinglists.data.db.dao.ShoppingListsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ShoppingListsDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingListsDatabase::class.java,
            "shopping_lists_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideShoppingListsDAO(shoppingListsDatabase: ShoppingListsDatabase): ShoppingListsDAO {
        return shoppingListsDatabase.ShoppingListsDAO()
    }

}