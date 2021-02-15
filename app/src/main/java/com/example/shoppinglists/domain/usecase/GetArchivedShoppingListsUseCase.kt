package com.example.shoppinglists.domain.usecase

import com.example.shoppinglists.domain.repository.ShoppingListsRepository

class GetArchivedShoppingListsUseCase(private val shoppingListsRepository: ShoppingListsRepository) {

    fun execute() = shoppingListsRepository.getArchivedShoppingLists()

}