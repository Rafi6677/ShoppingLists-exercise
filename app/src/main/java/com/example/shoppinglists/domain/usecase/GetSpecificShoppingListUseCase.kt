package com.example.shoppinglists.domain.usecase

import com.example.shoppinglists.domain.repository.ShoppingListsRepository

class GetSpecificShoppingListUseCase(private val shoppingListsRepository: ShoppingListsRepository) {

    suspend fun execute(id: Int) = shoppingListsRepository.getSpecificShoppingList(id)

}