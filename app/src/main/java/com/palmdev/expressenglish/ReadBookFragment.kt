package com.palmdev.expressenglish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.databinding.FragmentBookReadBinding

class ReadBookFragment: Fragment(R.layout.fragment_book_read) {


    private lateinit var binding: FragmentBookReadBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookReadBinding.bind(view)

        val bookID = requireArguments().getInt(BOOK_ID)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,false)
        }
    }

    companion object {
        const val BOOK_ID = "1234"
    }
}