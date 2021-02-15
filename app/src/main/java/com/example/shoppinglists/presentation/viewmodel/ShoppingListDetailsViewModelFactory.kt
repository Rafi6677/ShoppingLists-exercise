package com.example.shoppinglists.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglists.domain.usecase.*
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ShoppingListDetailsViewModelFactory(
    private val app: Application,
    private val addShoppingListUseCase: AddShoppingListUseCase,
    private val getSpecificShoppingListUseCase: GetSpecificShoppingListUseCase,
    private val editShoppingListUseCase: EditShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListDetailsViewModel::class.java)) {
            return ShoppingListDetailsViewModel(
                app,
                addShoppingListUseCase,
                getSpecificShoppingListUseCase,
                editShoppingListUseCase,
                deleteShoppingListUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}