package com.example.shoppinglists.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoppinglists.databinding.ActivityMainBinding
import com.example.shoppinglists.presentation.adapter.ShoppingListsAdapter
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModel
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ShoppingListsViewModelFactory
    @Inject
    lateinit var adapter: ShoppingListsAdapter

    lateinit var viewModel: ShoppingListsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigation.setupWithNavController(
            fragment.findNavController()
        )

        viewModel = ViewModelProvider(this, factory)
            .get(ShoppingListsViewModel::class.java)
    }

    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }

}