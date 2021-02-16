package com.example.shoppinglists.presentation.di

import android.app.Application
import com.example.shoppinglists.domain.usecase.*
import com.example.shoppinglists.presentation.viewmodel.ShoppingListDetailsViewModelFactory
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideShoppingListsViewModel(
        app: Application,
        getShoppingListsUseCase: GetShoppingListsUseCase,
        getArchivedShoppingListsUseCase: GetArchivedShoppingListsUseCase
    ): ShoppingListsViewModelFactory {
        return ShoppingListsViewModelFactory(
            app,
            getShoppingListsUseCase,
            getArchivedShoppingListsUseCase
        )
    }

    @Singleton
    @Provides
    fun provideShoppingListDetailsViewModelFactory(
        app: Application,
        addShoppingListUseCase: AddShoppingListUseCase,
        editShoppingListUseCase: EditShoppingListUseCase,
        deleteShoppingListUseCase: DeleteShoppingListUseCase
    ): ShoppingListDetailsViewModelFactory {
        return ShoppingListDetailsViewModelFactory(
            app,
            addShoppingListUseCase,
            editShoppingListUseCase,
            deleteShoppingListUseCase
        )
    }

}