package com.example.shoppinglists.presentation.di

import com.example.shoppinglists.data.repository.ShoppingListsRepositoryImpl
import com.example.shoppinglists.data.repository.datasource.ShoppingListsDataSource
import com.example.shoppinglists.domain.repository.ShoppingListsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideShoppingListsRepository(
        shoppingListsDataSource: ShoppingListsDataSource
    ): ShoppingListsRepository {
        return ShoppingListsRepositoryImpl(shoppingListsDataSource)
    }

}