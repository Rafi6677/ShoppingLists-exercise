package com.example.shoppinglists.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglists.R
import com.example.shoppinglists.databinding.FragmentShoppingListsBinding
import com.example.shoppinglists.presentation.MainActivity
import com.example.shoppinglists.presentation.adapter.ShoppingListsAdapter
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModel
import com.example.shoppinglists.utils.Resource

class ShoppingListsFragment : Fragment() {

    private lateinit var viewModel: ShoppingListsViewModel
    private lateinit var binding: FragmentShoppingListsBinding
    private lateinit var adapter: ShoppingListsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).setActionBarTitle(
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
        viewModel = (activity as MainActivity).viewModel
        adapter = (activity as MainActivity).adapter

        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_shopping_list", it)
            }
            findNavController().navigate(
                R.id.action_shoppingListsFragment_to_shoppingListDetailsFragment,
                bundle
            )
        }

        initRecyclerView()
        displayShoppingLists()
    }

    private fun initRecyclerView() {
        val divider = DividerItemDecoration(
            binding.shoppingListsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )

        binding.shoppingListsRecyclerView.apply {
            adapter = this.adapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(divider)
        }
    }

    private fun displayShoppingLists() {
        hideNoDataInfo()
        showProgressBar()

        val response = viewModel.getShoppingLists()

        response.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                adapter.differ.submitList(it)
                hideProgressBar()
            } else {
                hideProgressBar()
                showNoDataInfo()
            }
        })
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