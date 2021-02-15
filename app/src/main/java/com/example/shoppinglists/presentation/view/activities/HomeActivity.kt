package com.example.shoppinglists.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoppinglists.databinding.ActivityHomeBinding
import com.example.shoppinglists.presentation.adapter.ShoppingListsAdapter
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModel
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ShoppingListsViewModelFactory
    @Inject
    lateinit var adapter: ShoppingListsAdapter

    lateinit var viewModel: ShoppingListsViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
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