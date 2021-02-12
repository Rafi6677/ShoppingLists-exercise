package com.example.shoppinglists.presentation.di

import com.example.shoppinglists.presentation.adapter.ProductsAdapter
import com.example.shoppinglists.presentation.adapter.ShoppingListsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideShoppingListsAdapter(): ShoppingListsAdapter {
        return ShoppingListsAdapter()
    }

    @Singleton
    @Provides
    fun provideProductsAdapter(): ProductsAdapter {
        return ProductsAdapter()
    }

}