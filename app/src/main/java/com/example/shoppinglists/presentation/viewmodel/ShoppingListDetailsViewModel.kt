package com.example.shoppinglists.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.domain.usecase.AddShoppingListUseCase
import com.example.shoppinglists.domain.usecase.DeleteShoppingListUseCase
import com.example.shoppinglists.domain.usecase.EditShoppingListUseCase
import com.example.shoppinglists.domain.usecase.GetSpecificShoppingListUseCase
import kotlinx.coroutines.launch

class ShoppingListDetailsViewModel(
    private val app: Application,
    private val addShoppingListUseCase: AddShoppingListUseCase,
    private val getSpecificShoppingListUseCase: GetSpecificShoppingListUseCase,
    private val editShoppingListUseCase: EditShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : AndroidViewModel(app){

    fun saveShoppingList(shoppingList: ShoppingList) = viewModelScope.launch {
        addShoppingListUseCase.execute(shoppingList)
    }

}