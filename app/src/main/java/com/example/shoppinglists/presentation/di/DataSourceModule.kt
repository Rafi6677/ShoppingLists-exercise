package com.example.shoppinglists.presentation.di

import com.example.shoppinglists.data.db.dao.ShoppingListsDAO
import com.example.shoppinglists.data.repository.datasource.ShoppingListsDataSource
import com.example.shoppinglists.data.repository.datasourceimpl.ShoppingListsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideShoppingListsDataSource(
        dao: ShoppingListsDAO
    ): ShoppingListsDataSource {
        return ShoppingListsDataSourceImpl(dao)
    }

}