package com.example.shoppinglists.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglists.domain.usecase.DeleteShoppingListUseCase
import com.example.shoppinglists.domain.usecase.GetArchivedShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetSpecificShoppingListUseCase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ShoppingListsViewModelFactory(
    private val app: Application,
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val getArchivedShoppingListsUseCase: GetArchivedShoppingListsUseCase,
    private val getSpecificShoppingListUseCase: GetSpecificShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListsViewModel::class.java)) {
            return ShoppingListsViewModel(
                app,
                getShoppingListsUseCase,
                getArchivedShoppingListsUseCase,
                getSpecificShoppingListUseCase,
                deleteShoppingListUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}