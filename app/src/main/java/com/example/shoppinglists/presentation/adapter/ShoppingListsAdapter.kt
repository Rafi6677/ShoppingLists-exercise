package com.example.shoppinglists.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglists.data.db.model.ShoppingList
import com.example.shoppinglists.databinding.ItemShoppingListBinding
import com.example.shoppinglists.utils.CalculationUtils

class ShoppingListsAdapter : RecyclerView.Adapter<ShoppingListsAdapter.ShoppingListsViewHolder>() {

    private var onItemClickListener: ((ShoppingList)->Unit)? = null

    private val callback = object: DiffUtil.ItemCallback<ShoppingList>() {
        override fun areItemsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListsViewHolder {
        val binding = ItemShoppingListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ShoppingListsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListsViewHolder, position: Int) {
        val shoppingList = differ.currentList[position]
        holder.bind(shoppingList)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (ShoppingList)->Unit) {
        onItemClickListener = listener
    }

    inner class ShoppingListsViewHolder(
        val binding: ItemShoppingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(shoppingList: ShoppingList) {
            val allProductsQuantity = shoppingList.productsList.size
            val boughtProductsQuantity = CalculationUtils.countBoughtProducts(shoppingList.productsList)

            binding.shoppingListTitleTextView.text = CalculationUtils
                    .formatDateFromTimestampToString(shoppingList.timestamp)
            binding.productsQuantityTextView.text = "Groceries done: $boughtProductsQuantity/$allProductsQuantity"

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(shoppingList)
                }
            }
        }

    }

}

