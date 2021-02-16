package com.example.shoppinglists.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shoppinglists.domain.usecase.GetArchivedShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetShoppingListsUseCase

class ShoppingListsViewModel(
    private val app: Application,
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val getArchivedShoppingListsUseCase: GetArchivedShoppingListsUseCase
) : AndroidViewModel(app) {

    val shoppingLists = getShoppingListsUseCase.execute()
    val archivedShoppingLists = getArchivedShoppingListsUseCase.execute()

}