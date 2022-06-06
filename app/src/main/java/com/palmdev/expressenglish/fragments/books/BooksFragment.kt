package com.palmdev.expressenglish.fragments.books

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.BooksAdapter
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentBooksBinding

class BooksFragment : Fragment(R.layout.fragment_books) {

    private lateinit var mBinding: FragmentBooksBinding
    private val mAdapter = BooksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentBooksBinding.bind(view)

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
        val numberOfSelectedWords = User.getSelectedWords()
        mBinding.numberOfSelectedWords.text = numberOfSelectedWords.toString()
        mBinding.btnSelectedWords.setOnClickListener {
            findNavController().navigate(R.id.action_booksFragment_to_wordsFragment)
        }

        // Set OnBackPressed Callback
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        mBinding.btnAll.isChecked = true

        // Firebase Event
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, BooksFragment().javaClass.simpleName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, BooksFragment().javaClass.simpleName)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    private fun setRecViewAllItems() = with(mBinding) {
        mAdapter.clearData()
        // Init all books
        Books.initAllBooks()
        val data = Books.getBooks()
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

    private fun setRecViewA1Items() = with(mBinding) {
        mAdapter.clearData()
        val data = Books.getA1Books()
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

    private fun setRecViewA2Items() = with(mBinding) {
        mAdapter.clearData()
        val data = Books.getA2Books()
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

    private fun setRecViewB1Items() = with(mBinding) {
        mAdapter.clearData()
        val data = Books.getB1Books()
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

    private fun setRecViewB2Items() = with(mBinding) {
        mAdapter.clearData()
        val data = Books.getB2Books()
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

    private fun setRecViewC1Items() = with(mBinding) {
        mAdapter.clearData()
        val data = Books.getC1Books()
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

    private fun setRecViewLikedItems() = with(mBinding) {
        mAdapter.clearData()
        val data = Books.getLikedBooks()
        if (mAdapter.itemCount == 0) {
            mAdapter.addBooks(data)
        }
    }

}