package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.BooksAdapter
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentBooksBinding

class BooksFragment : Fragment(R.layout.fragment_books) {

    private lateinit var mBinding: FragmentBooksBinding
    private val mAdapter = BooksAdapter()
    private lateinit var mCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentBooksBinding.bind(view)

        setOnBackPressedCallback()

        // Init Recycler View
        mBinding.recView.layoutManager = LinearLayoutManager(view.context)
        mBinding.recView.adapter = mAdapter
        setRecViewAllItems()
        // Init Sort Buttons
        mBinding.apply {
            btnAll.isChecked = true
            btnAll.setOnClickListener {
                setRecViewAllItems()
            }
            btnA1.setOnClickListener {
                setRecViewA1Items()
            }
            btnA2.setOnClickListener {
                setRecViewA2Items()
            }
            btnB1.setOnClickListener {
                setRecViewB1Items()
            }
            btnB2.setOnClickListener {
                setRecViewB2Items()
            }
            btnC1.setOnClickListener {
                setRecViewC1Items()
            }
            btnLiked.setOnClickListener {
                setRecViewLikedItems()
            }
        }

        // Selected Words
        val numberOfSelectedWords = SharedPref.get(SharedPref.SELECTED_WORDS, 0)
        mBinding.numberOfSelectedWords.text = numberOfSelectedWords.toString()
        mBinding.btnSelectedWords.setOnClickListener {
            findNavController().navigate(R.id.action_booksFragment_to_wordsFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        mBinding.btnAll.isChecked = true
    }

    override fun onPause() {
        super.onPause()
        mCallback.remove()
    }

    private fun setOnBackPressedCallback(){
        mCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeFragment)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }

    private fun setRecViewAllItems() = with(mBinding) {
        mAdapter.clearData()
        // Init all books
        var data = Books.getBooks()
        if (data.size == 0) {
            Books.initAllBooks()
            data = Books.getBooks()
        }
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }
    private fun setRecViewA1Items() = with(mBinding) {
        mAdapter.clearData()

        var data = Books.getA1Books()
        if (data.size == 0) {
            Books.initAllBooks()
            data = Books.getA1Books()
        }
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }
    private fun setRecViewA2Items() = with(mBinding) {
        mAdapter.clearData()

        var data = Books.getA2Books()
        if (data.size == 0) {
            Books.initAllBooks()
            data = Books.getA2Books()
        }
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }
    private fun setRecViewB1Items() = with(mBinding) {
        mAdapter.clearData()

        var data = Books.getB1Books()
        if (data.size == 0) {
            Books.initAllBooks()
            data = Books.getB1Books()
        }
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }
    private fun setRecViewB2Items() = with(mBinding) {
        mAdapter.clearData()

        var data = Books.getB2Books()
        if (data.size == 0) {
            Books.initAllBooks()
            data = Books.getB2Books()
        }
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }
    private fun setRecViewC1Items() = with(mBinding) {
        mAdapter.clearData()

        var data = Books.getC1Books()
        if (data.size == 0) {
            Books.initAllBooks()
            data = Books.getC1Books()
        }
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }
    private fun setRecViewLikedItems() = with(mBinding) {
        mAdapter.clearData()

        var data = Books.getLikedBooks()
        if (data.size == 0) {
            Books.initAllBooks()
            data = Books.getLikedBooks()
        }
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

}