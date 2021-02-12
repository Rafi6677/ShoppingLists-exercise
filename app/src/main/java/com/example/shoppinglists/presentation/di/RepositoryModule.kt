package com.example.shoppinglists.presentation.di

import com.example.shoppinglists.data.repository.ShoppingListsRepositoryImpl
import com.example.shoppinglists.data.repository.datasource.ShoppingListsDataSource
import com.example.shoppinglists.domain.repository.ShoppingListsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideShoppingListsRepository(
        shoppingListsDataSource: ShoppingListsDataSource
    ): ShoppingListsRepository {
        return ShoppingListsRepositoryImpl(shoppingListsDataSource)
    }

}