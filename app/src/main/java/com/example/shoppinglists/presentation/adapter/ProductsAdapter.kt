package com.example.shoppinglists.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglists.R
import com.example.shoppinglists.data.db.model.Product
import com.example.shoppinglists.databinding.ItemProductBinding
import com.example.shoppinglists.presentation.enums.ShoppingListOperationType

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val productsList = ArrayList<Product>()
    private lateinit var shoppingListOperationType: ShoppingListOperationType
    private lateinit var productOperationButtonClickListener: (Product)->Unit

    fun setOperationType(shoppingListOperationType: ShoppingListOperationType) {
        this.shoppingListOperationType = shoppingListOperationType
    }

    fun setProductsList(productsList: List<Product>?) {
        this.productsList.clear()

        if (productsList != null) {
            this.productsList.addAll(productsList)
        }
    }

    fun getProductsList(): ArrayList<Product> {
        return productsList
    }

    fun setProductOperationButtonClickListener(clickListener: (Product)->Unit) {
        productOperationButtonClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = productsList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    inner class ProductsViewHolder(
        val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            binding.productNameTextView.text = product.name
            binding.productsQuantityTextView.text = "x${product.quantity}"

            when (shoppingListOperationType) {
                ShoppingListOperationType.Add,
                ShoppingListOperationType.Edit -> {
                    binding.productOperationImageView.setImageResource(R.drawable.ic_delete)
                    binding.productOperationImageView.setOnClickListener {
                        productOperationButtonClickListener(product)
                    }
                }
                ShoppingListOperationType.ShowDetails -> {
                    if (product.isBought) {
                        binding.productOperationImageView.setImageResource(R.drawable.ic_checked)
                    } else {
                        binding.productOperationImageView.setImageResource(R.drawable.ic_unchecked)
                    }

                    binding.productOperationImageView.setOnClickListener {
                        productOperationButtonClickListener(product)
                    }
                }
                ShoppingListOperationType.ShowArchivedDetails -> {
                    if (product.isBought) {
                        binding.productOperationImageView.setImageResource(R.drawable.ic_checked)
                    } else {
                        binding.productOperationImageView.setImageResource(R.drawable.ic_unchecked)
                    }
                }
            }
        }

    }

}
