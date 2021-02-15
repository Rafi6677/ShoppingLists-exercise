package com.example.shoppinglists.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shoppinglists.domain.usecase.DeleteShoppingListUseCase
import com.example.shoppinglists.domain.usecase.GetArchivedShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetSpecificShoppingListUseCase

class ShoppingListsViewModel(
    private val app: Application,
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val getArchivedShoppingListsUseCase: GetArchivedShoppingListsUseCase,
    private val getSpecificShoppingListUseCase: GetSpecificShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : AndroidViewModel(app) {

    val shoppingLists = getShoppingListsUseCase.execute()

}