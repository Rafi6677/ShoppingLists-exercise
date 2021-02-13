package com.example.shoppinglists.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.domain.usecase.DeleteShoppingListUseCase
import com.example.shoppinglists.domain.usecase.GetArchivedShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetShoppingListsUseCase
import com.example.shoppinglists.domain.usecase.GetSpecificShoppingListUseCase
import com.example.shoppinglists.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ShoppingListsViewModel(
    private val app: Application,
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val getArchivedShoppingListsUseCase: GetArchivedShoppingListsUseCase,
    private val getSpecificShoppingListUseCase: GetSpecificShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : AndroidViewModel(app) {

    fun getShoppingLists() = liveData {
        val shoppingLists = getShoppingListsUseCase.execute()
        emit(shoppingLists)
    }

}