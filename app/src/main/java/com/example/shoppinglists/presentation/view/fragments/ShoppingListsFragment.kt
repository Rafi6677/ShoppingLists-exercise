package com.example.shoppinglists.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglists.R
import com.example.shoppinglists.presentation.view.activities.ShoppingListDetailsActivity
import com.example.shoppinglists.databinding.FragmentShoppingListsBinding
import com.example.shoppinglists.presentation.view.activities.HomeActivity
import com.example.shoppinglists.presentation.adapter.ShoppingListsAdapter
import com.example.shoppinglists.presentation.enums.ShoppingListOperationType
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModel

class ShoppingListsFragment : Fragment() {

    private lateinit var viewModel: ShoppingListsViewModel
    private lateinit var binding: FragmentShoppingListsBinding
    private lateinit var adapter: ShoppingListsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as HomeActivity).setActionBarTitle(
            resources.getString(R.string.app_name)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_shopping_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentShoppingListsBinding.bind(view)
        viewModel = (activity as HomeActivity).viewModel
        adapter = (activity as HomeActivity).adapter

        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putInt("shopping_list_operation_type", ShoppingListOperationType.ShowDetails.value)
                putSerializable("shopping_list", it)
            }
            val intent = Intent(activity, ShoppingListDetailsActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }

        initRecyclerView()
        displayShoppingLists()
        initButtons()
    }

    private fun initRecyclerView() {
        val divider = DividerItemDecoration(
            binding.shoppingListsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )

        binding.shoppingListsRecyclerView.apply {
            adapter = this@ShoppingListsFragment.adapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(divider)
        }
    }

    private fun displayShoppingLists() {
        hideNoDataInfo()
        showProgressBar()

        viewModel.shoppingLists.observe(viewLifecycleOwner, {
            adapter.differ.submitList(it)
            hideProgressBar()

            if (it.isNotEmpty()) {
                hideNoDataInfo()
            } else {
                showNoDataInfo()
            }
        })
    }

    private fun initButtons() {
        binding.addButton.setOnClickListener {
            val intent = Intent(activity, ShoppingListDetailsActivity::class.java)
            intent.putExtra("shopping_list_operation_type", ShoppingListOperationType.Add.value)
            startActivity(intent)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showNoDataInfo() {
        binding.noDataInfoTextView.visibility = View.VISIBLE
    }

    private fun hideNoDataInfo() {
        binding.noDataInfoTextView.visibility = View.INVISIBLE
    }

}