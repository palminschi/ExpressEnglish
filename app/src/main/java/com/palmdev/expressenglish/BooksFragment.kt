package com.palmdev.expressenglish

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.databinding.FragmentBooksBinding

class BooksFragment: Fragment(R.layout.fragment_books) {

    private lateinit var binding: FragmentBooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBooksBinding.bind(view)

        binding.btnReadBook.setOnClickListener {
            findNavController().navigate(
                R.id.action_booksFragment_to_readBookFragment,
                bundleOf(ReadBookFragment.BOOK_ID to 1232)
                )
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}