package com.example.shoppinglists.domain.usecase

import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.domain.repository.ShoppingListsRepository
import com.example.shoppinglists.utils.Resource

class GetShoppingListsUseCase(private val shoppingListsRepository: ShoppingListsRepository) {

    fun execute() = shoppingListsRepository.getShoppingLists()

}