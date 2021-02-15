package com.example.shoppinglists.presentation

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglists.R
import com.example.shoppinglists.data.db.model.Product
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.databinding.ActivityShoppingListDetailsBinding
import com.example.shoppinglists.presentation.adapter.ProductsAdapter
import com.example.shoppinglists.presentation.enums.ShoppingListOperationType
import com.example.shoppinglists.presentation.viewmodel.ShoppingListDetailsViewModel
import com.example.shoppinglists.presentation.viewmodel.ShoppingListDetailsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_add_product_layout.*
import kotlinx.android.synthetic.main.dialog_add_product_layout.view.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ShoppingListDetailsViewModelFactory
    @Inject
    lateinit var adapter: ProductsAdapter

    private lateinit var viewModel: ShoppingListDetailsViewModel
    private lateinit var binding: ActivityShoppingListDetailsBinding
    private lateinit var shoppingListOperationType: ShoppingListOperationType
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)
                .get(ShoppingListDetailsViewModel::class.java)

        shoppingListOperationType = ShoppingListOperationType.setByValue(
                intent.getIntExtra("shopping_list_operation_type", 0)
        )!!

        adapter.setOperationType(shoppingListOperationType)

        manageAddFirstProductInfoVisibility()
        initRecyclerView()
        initButtons()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shopping_list_details_menu, menu)

        if (menu != null && shoppingListOperationType == ShoppingListOperationType.Add) {
            menu.findItem(R.id.menu_delete).isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> saveShoppingList()
            R.id.menu_delete -> deleteShoppingList()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        adapter.setProductsList(null)
        finish()
    }

    private fun manageAddFirstProductInfoVisibility() {
        if (adapter.getProductsList().isNotEmpty()) {
            binding.addFirstProductTextView.visibility = View.INVISIBLE
        } else {
            binding.addFirstProductTextView.visibility = View.VISIBLE
        }
    }

    private fun initRecyclerView() {
        val divider = DividerItemDecoration(
            binding.productsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )

        binding.productsRecyclerView.apply {
            adapter = this@ShoppingListDetailsActivity.adapter
            layoutManager = LinearLayoutManager(this@ShoppingListDetailsActivity)
            addItemDecoration(divider)
        }
    }

    private fun initButtons() {
        binding.addButton.setOnClickListener {
            val dialogLayout = layoutInflater.inflate(R.layout.dialog_add_product_layout, null)
            dialogLayout.quantity_numberPicker.apply {
                minValue = 1
                maxValue = 100
            }
            val builder = AlertDialog.Builder(this).apply {
                setTitle(resources.getString(R.string.new_product))
                setView(dialogLayout)
                setPositiveButton("Add") { _, _ ->
                    addProduct(
                        dialogLayout.product_name_editText.text.toString(),
                        dialogLayout.quantity_numberPicker.value
                    )
                }
                setNegativeButton("Cancel") { _, _ -> }
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun addProduct(name: String, quantity: Int) {
        if (name.isEmpty()) {
            Toast.makeText(this, "Add product name", Toast.LENGTH_SHORT)
                .show()
        } else {
            val product = Product(name, quantity, false)


            adapter.addProduct(product)
            manageAddFirstProductInfoVisibility()
            adapter.notifyDataSetChanged()
        }
    }

    private fun saveShoppingList() {
        val products = ArrayList<Product>()
        products.addAll(adapter.getProductsList())

        if (products.isEmpty()) {
            Toast.makeText(this, resources.getString(R.string.empty_shopping_list), Toast.LENGTH_SHORT)
                    .show()
            return
        }

        val timestamp = Date().time
        val shoppingList = ShoppingList(0, "title", products, timestamp, false)

        viewModel.saveShoppingList(shoppingList)
        adapter.setProductsList(null)
        finish()
    }

    private fun deleteShoppingList() {

    }

}