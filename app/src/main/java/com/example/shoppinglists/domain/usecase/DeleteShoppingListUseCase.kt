package com.example.shoppinglists.domain.usecase

import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.domain.repository.ShoppingListsRepository

class DeleteShoppingListUseCase(private val shoppingListsRepository: ShoppingListsRepository) {

    suspend fun execute(shoppingList: ShoppingList) = shoppingListsRepository
        .deleteShoppingList(shoppingList)

}