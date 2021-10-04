package com.palmdev.expressenglish

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.databinding.FragmentBookReadBinding

class ReadBookFragment: Fragment(R.layout.fragment_book_read) {


    private lateinit var binding: FragmentBookReadBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookReadBinding.bind(view)

      //  val bookID = requireArguments().getInt(BOOK_ID)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        val bundle = Bundle()
        binding.bookID.text = requireArguments().getString(BOOK_KEY)
    }

    companion object {
        const val BOOK_KEY = "BOOK_KEY"
        const val ID_BOOK_001 = "001"
        const val ID_BOOK_002 = "002"
        const val ID_BOOK_003 = "002"
        const val ID_BOOK_004 = "004"
        const val ID_BOOK_005 = "005"
        const val ID_BOOK_006 = "006"
        const val ID_BOOK_007 = "007"
        const val ID_BOOK_008 = "008"
        const val ID_BOOK_009 = "009"
        const val ID_BOOK_010 = "010"
    }
}