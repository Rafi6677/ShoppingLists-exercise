package com.example.shoppinglists.presentation.di

import com.example.shoppinglists.presentation.adapter.ShoppingListDetailsAdapter
import com.example.shoppinglists.presentation.adapter.ShoppingListsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideShoppingListsAdapter(): ShoppingListsAdapter {
        return ShoppingListsAdapter()
    }

    @Singleton
    @Provides
    fun provideShoppingListDetailsAdapter(): ShoppingListDetailsAdapter {
        return ShoppingListDetailsAdapter()
    }

}