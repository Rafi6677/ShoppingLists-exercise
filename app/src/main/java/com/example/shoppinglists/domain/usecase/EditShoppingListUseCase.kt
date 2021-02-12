package com.example.shoppinglists.domain.usecase

import com.example.shoppinglists.data.model.ShoppingList
import com.example.shoppinglists.domain.repository.ShoppingListsRepository

class EditShoppingListUseCase(private val shoppingListsRepository: ShoppingListsRepository) {

    suspend fun execute(shoppingList: ShoppingList) = shoppingListsRepository
        .editShoppingList(shoppingList)

}