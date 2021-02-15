package com.example.shoppinglists.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppinglists.R
import com.example.shoppinglists.presentation.view.activities.HomeActivity

class ArchivedShoppingListsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as HomeActivity).setActionBarTitle(
            resources.getString(R.string.archived_shopping_lists)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_archived_shopping_lists, container, false)
    }

}