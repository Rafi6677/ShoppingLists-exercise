package com.example.shoppinglists.domain.usecase

import com.example.shoppinglists.domain.repository.ShoppingListsRepository

class GetShoppingListsUseCase(private val shoppingListsRepository: ShoppingListsRepository) {

    fun execute() = shoppingListsRepository.getShoppingLists()

}