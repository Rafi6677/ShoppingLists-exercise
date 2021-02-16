package com.example.shoppinglists.presentation.di

import com.example.shoppinglists.domain.repository.ShoppingListsRepository
import com.example.shoppinglists.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideAddShoppingListsUseCase(
        shoppingListsRepository: ShoppingListsRepository
    ): AddShoppingListUseCase {
        return AddShoppingListUseCase(shoppingListsRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteShoppingListUseCase(
        shoppingListsRepository: ShoppingListsRepository
    ): DeleteShoppingListUseCase {
        return DeleteShoppingListUseCase(shoppingListsRepository)
    }

    @Singleton
    @Provides
    fun provideEditShoppingListUseCase(
        shoppingListsRepository: ShoppingListsRepository
    ): EditShoppingListUseCase {
        return EditShoppingListUseCase(shoppingListsRepository)
    }

    @Singleton
    @Provides
    fun provideGetArchivedShoppingListsUseCase(
        shoppingListsRepository: ShoppingListsRepository
    ): GetArchivedShoppingListsUseCase {
        return GetArchivedShoppingListsUseCase(shoppingListsRepository)
    }

    @Singleton
    @Provides
    fun provideGetShoppingListsUseCase(
        shoppingListsRepository: ShoppingListsRepository
    ): GetShoppingListsUseCase {
        return GetShoppingListsUseCase(shoppingListsRepository)
    }

}