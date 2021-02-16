package com.example.shoppinglists.presentation.view.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
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
import com.example.shoppinglists.presentation.enums.DatabaseOperation
import com.example.shoppinglists.presentation.enums.ShoppingListOperationType
import com.example.shoppinglists.presentation.viewmodel.ShoppingListDetailsViewModel
import com.example.shoppinglists.presentation.viewmodel.ShoppingListDetailsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_add_product_layout.*
import kotlinx.android.synthetic.main.dialog_add_product_layout.view.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ShoppingListDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ShoppingListDetailsViewModelFactory
    @Inject
    lateinit var adapter: ProductsAdapter

    private lateinit var viewModel: ShoppingListDetailsViewModel
    private lateinit var binding: ActivityShoppingListDetailsBinding
    private lateinit var shoppingListOperationType: ShoppingListOperationType
    private lateinit var shoppingList: ShoppingList
    
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = resources.getString(R.string.shopping_list)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        binding = ActivityShoppingListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)
                .get(ShoppingListDetailsViewModel::class.java)

        initData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shopping_list_details_menu, menu)

        if (menu != null) {
            when (shoppingListOperationType) {
                ShoppingListOperationType.Add,
                ShoppingListOperationType.Edit -> {
                    menu.findItem(R.id.menu_edit).isVisible = false
                    menu.findItem(R.id.menu_archive).isVisible = false
                    menu.findItem(R.id.menu_delete).isVisible = false
                }
                ShoppingListOperationType.ShowDetails -> {
                    menu.findItem(R.id.menu_delete).isVisible = false
                }
                ShoppingListOperationType.ShowArchivedDetails -> {
                    menu.findItem(R.id.menu_edit).isVisible = false
                    menu.findItem(R.id.menu_save).isVisible = false
                    menu.findItem(R.id.menu_archive).isVisible = false
                }
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.menu_edit -> editShoppingList()
            R.id.menu_save -> saveShoppingList()
            R.id.menu_archive -> archiveShoppingList()
            R.id.menu_delete -> deleteShoppingList()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        adapter.setProductsList(null)
    }

    private fun initData() {
        val bundle = intent.extras!!
        shoppingListOperationType = ShoppingListOperationType.setByValue(
            bundle.getInt("shopping_list_operation_type")
        )!!

        adapter.setOperationType(shoppingListOperationType)

        when (shoppingListOperationType) {
            ShoppingListOperationType.Add -> {
                adapter.setProductOperationButtonClickListener {
                    deleteProduct(it)
                }
            }
            ShoppingListOperationType.Edit -> {
                shoppingList = bundle.getSerializable("shopping_list") as ShoppingList
                adapter.setProductOperationButtonClickListener {
                    deleteProduct(it)
                }
                adapter.setProductsList(shoppingList.productsList)
            }
            ShoppingListOperationType.ShowDetails -> {
                shoppingList = bundle.getSerializable("shopping_list") as ShoppingList
                binding.addButton.visibility = View.GONE
                adapter.setProductOperationButtonClickListener {
                    changeProductState(it)
                }
                if (adapter.getProductsList().isEmpty()) {
                    adapter.setProductsList(shoppingList.productsList)
                }
            }
            ShoppingListOperationType.ShowArchivedDetails -> {
                shoppingList = bundle.getSerializable("shopping_list") as ShoppingList
                binding.addButton.visibility = View.GONE
                adapter.setProductsList(shoppingList.productsList)
            }
        }

        manageAddFirstProductInfoVisibility()
        initRecyclerView()
        initButtons()
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

    @SuppressLint("InflateParams")
    private fun initButtons() {
        binding.addButton.setOnClickListener {
            val dialogLayout = layoutInflater.inflate(R.layout.dialog_add_product_layout, null)
            dialogLayout.quantity_numberPicker.apply {
                minValue = 1
                maxValue = 100
            }
            AlertDialog.Builder(this).apply {
                setTitle(resources.getString(R.string.new_product))
                setView(dialogLayout)
                setPositiveButton("Add") { _, _ ->
                    addProduct(
                        dialogLayout.product_name_editText.text.toString(),
                        dialogLayout.quantity_numberPicker.value
                    )
                }
                setNegativeButton("Cancel") { _, _ -> }
                create().show()
            }
        }
    }

    private fun addProduct(name: String, quantity: Int) {
        if (name.isEmpty()) {
            Toast.makeText(this, "Add product name", Toast.LENGTH_SHORT)
                .show()
        } else {
            val product = Product(name, quantity, false)
            adapter.getProductsList().add(product)
            manageAddFirstProductInfoVisibility()
            adapter.notifyDataSetChanged()
        }
    }

    private fun deleteProduct(product: Product) {
        adapter.getProductsList().remove(product)
        adapter.notifyDataSetChanged()
        manageAddFirstProductInfoVisibility()
    }

    private fun changeProductState(product: Product) {
        val isBought = product.isBought
        val productIndex = adapter.getProductsList().indexOf(product)
        adapter.getProductsList()[productIndex].isBought = !isBought
        adapter.notifyDataSetChanged()
    }

    private fun editShoppingList() {
        val products = ArrayList<Product>().apply {
            addAll(adapter.getProductsList())
        }
        val timestamp = Date().time
        val shoppingList = ShoppingList(this.shoppingList.id, products, timestamp, false)

        viewModel.saveShoppingList(shoppingList)

        val bundle = Bundle().apply {
            putInt("shopping_list_operation_type", ShoppingListOperationType.Edit.value)
            putSerializable("shopping_list", shoppingList)
        }
        val intent = Intent(this, ShoppingListDetailsActivity::class.java).apply {
            putExtras(bundle)
            addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        }
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
    }

    private fun saveShoppingList() {
        val products = ArrayList<Product>().apply {
            addAll(adapter.getProductsList())
        }

        if (products.isEmpty()) {
            Toast.makeText(this, resources.getString(R.string.empty_shopping_list), Toast.LENGTH_SHORT)
                    .show()
            return
        }

        val timestamp = Date().time

        if (shoppingListOperationType == ShoppingListOperationType.Add) {
            val shoppingList = ShoppingList(0, products, timestamp, false)
            viewModel.saveShoppingList(shoppingList)
            adapter.setProductsList(null)
            showInformationMessage(
                    this@ShoppingListDetailsActivity,
                    DatabaseOperation.Saved
            )
            finish()
        } else if (shoppingListOperationType == ShoppingListOperationType.ShowDetails
                || shoppingListOperationType == ShoppingListOperationType.Edit) {
            val shoppingList = ShoppingList(this.shoppingList.id, products, timestamp, false)
            viewModel.saveShoppingList(shoppingList)
            showInformationMessage(
                    this@ShoppingListDetailsActivity,
                    DatabaseOperation.Saved
            )
            adapter.setProductsList(null)
            finish()
        }
    }

    private fun archiveShoppingList() {
        AlertDialog.Builder(this).apply {
            setTitle(resources.getString(R.string.archive))
            setMessage(resources.getString(R.string.archive_confirmation))
            setPositiveButton("Yes") { _, _ ->
                val archivedShoppingList = ShoppingList(
                    shoppingList.id,
                    shoppingList.productsList,
                    shoppingList.timestamp,
                    true
                )
                viewModel.updateShoppingList(archivedShoppingList)
                adapter.setProductsList(null)
                showInformationMessage(
                        this@ShoppingListDetailsActivity,
                        DatabaseOperation.Archived
                )
                finish()
            }
            setNegativeButton("Cancel") {_,_-> }
            create().show()
        }
    }

    private fun deleteShoppingList() {
        AlertDialog.Builder(this).apply {
            setTitle(resources.getString(R.string.delete))
            setMessage(resources.getString(R.string.delete_confirmation))
            setPositiveButton("Yes") { _, _ ->
                viewModel.deleteShoppingList(shoppingList)
                adapter.setProductsList(null)
                showInformationMessage(
                    this@ShoppingListDetailsActivity,
                    DatabaseOperation.Deleted
                )
                finish()
            }
            setNegativeButton("Cancel") {_,_-> }
            create().show()
        }
    }

    private fun showInformationMessage(context: Context, databaseOperation: DatabaseOperation) {
        lateinit var toast: Toast

        when(databaseOperation) {
            DatabaseOperation.Saved -> {
                toast = Toast.makeText(
                    context,
                    resources.getString(R.string.saved_shopping_list),
                    Toast.LENGTH_SHORT
                )
            }
            DatabaseOperation.Archived -> {
                toast = Toast.makeText(
                        context,
                        resources.getString(R.string.archived_shopping_list),
                        Toast.LENGTH_SHORT
                )
            }
            DatabaseOperation.Deleted -> {
                toast = Toast.makeText(
                        context,
                        resources.getString(R.string.deleted_shopping_list),
                        Toast.LENGTH_SHORT
                )
            }
        }

        toast.show()
    }

}