package com.palmdev.expressenglish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_001
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_002
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_003
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_004
import com.palmdev.expressenglish.adapters.BookAdapter
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.databinding.FragmentBooksBinding
import com.palmdev.expressenglish.model.Book

class BooksFragment: Fragment(R.layout.fragment_books) {

    private lateinit var mBinding: FragmentBooksBinding
    private val mAdapter = BookAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentBooksBinding.bind(view)

        //binding.btnReadBook.setOnClickListener {
        //    findNavController().navigate(
         //       R.id.action_booksFragment_to_readBookFragment,
         //       bundleOf(ReadBookFragment.BOOK_ID to 1232)
         //       )
        //}

       // binding.btnBack.setOnClickListener {
       //     findNavController().popBackStack()
       // }

        mBinding.apply {
            recView.layoutManager = LinearLayoutManager(view.context)
            recView.adapter = mAdapter
            // Init all books
            Books.getBooks(mAdapter)
        }

    }

}