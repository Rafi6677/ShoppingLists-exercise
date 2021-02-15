package com.example.shoppinglists.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.shoppinglists.R
import com.example.shoppinglists.databinding.FragmentShoppingListDetailsBinding
import com.example.shoppinglists.presentation.ShoppingListsActivity
import com.example.shoppinglists.presentation.adapter.ProductsAdapter
import com.example.shoppinglists.presentation.viewmodel.ShoppingListsViewModel

class ShoppingListDetailsFragment : Fragment() {

    private lateinit var viewModel: ShoppingListsViewModel
    private lateinit var binding: FragmentShoppingListDetailsBinding
    private lateinit var adapter: ProductsAdapter
    private var shoppingListOperationType: Int = 0
    private lateinit var shoppingListTitle: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ShoppingListDetailsFragmentArgs by navArgs()
        //shoppingListOperationType = args.shoppingListOperationType

        binding = FragmentShoppingListDetailsBinding.bind(view)
        viewModel = (activity as ShoppingListsActivity).viewModel

        showTitleDialog()
    }

    private fun showTitleDialog() {
        /*val dialogLayout = layoutInflater.inflate(R.layout.dialog_custom_shopping_list_title_layout, null)
        val dialogBinding = DialogCustomShoppingListTitleLayoutBinding.bind(dialogLayout)
        AlertDialog.Builder(activity).apply {
            setTitle(resources.getString(R.string.set_title))
            setView(dialogLayout)
            setPositiveButton("Save") { _, _ ->
                val title = dialogBinding.titleEditText.toString()

                if (title.isNotEmpty()) {
                    shoppingListTitle = title
                } else {
                    Toast.makeText(activity, "Title is empty", Toast.LENGTH_SHORT)
                            .show()
                }
            }

        }*/
    }

}