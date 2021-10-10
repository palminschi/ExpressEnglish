package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.BookAdapter
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.databinding.FragmentBooksBinding

class BooksFragment : Fragment(R.layout.fragment_books) {

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

        initRecyclerView()


    }



    private fun initRecyclerView() {
        mBinding.apply {
            recView.layoutManager = LinearLayoutManager(view?.context)
            recView.adapter = mAdapter

            // Init all books
            val data = Books.getBooks()
            if (data.size == 0){
                Books.initBooks()
            }
            if (mAdapter.itemCount == 0) {
                mAdapter.addBooks(data)
            }
        }
    }

    companion object {
        const val BOOK_ID_KEY = "BOOK_KEY"
        const val ID_BOOK_001 = "ID_BOOK_001"
        const val ID_BOOK_002 = "ID_BOOK_002"
        const val ID_BOOK_003 = "ID_BOOK_003"
        const val ID_BOOK_004 = "ID_BOOK_004"
        const val ID_BOOK_005 = "ID_BOOK_005"
        const val ID_BOOK_006 = "ID_BOOK_006"
        const val ID_BOOK_007 = "ID_BOOK_007"
        const val ID_BOOK_008 = "ID_BOOK_008"
        const val ID_BOOK_009 = "ID_BOOK_009"
        const val ID_BOOK_010 = "ID_BOOK_010"
    }

}