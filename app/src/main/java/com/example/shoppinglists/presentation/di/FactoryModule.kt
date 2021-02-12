package com.example.shoppinglists.presentation.di

import android.app.Application
import com.example.shoppinglists.domain.usecase.DeleteShoppingListUseCase
import com.example.shoppinglists.domain.usecase.GetArchivedShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetSpecificShoppingListUseCase
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideShoppingListsViewModel(
        app: Application,
        getShoppingListsUseCase: GetShoppingListsUseCase,
        getArchivedShoppingListsUseCase: GetArchivedShoppingListsUseCase,
        getSpecificShoppingListUseCase: GetSpecificShoppingListUseCase,
        deleteShoppingListUseCase: DeleteShoppingListUseCase
    ): ShoppingListsViewModelFactory {
        return ShoppingListsViewModelFactory(
            app,
            getShoppingListsUseCase,
            getArchivedShoppingListsUseCase,
            getSpecificShoppingListUseCase,
            deleteShoppingListUseCase
        )
    }

}