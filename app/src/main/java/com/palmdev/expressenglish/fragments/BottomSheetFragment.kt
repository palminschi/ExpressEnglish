package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentBottomSheetBinding


class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root

        // Realization the Button Switch Theme
        binding.apply {
            switchTheme.isChecked = SharedPref.read(SharedPref.BOOK_DARK_MODE, true)
            switchTheme.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    SharedPref.write(SharedPref.BOOK_DARK_MODE, true)
                } else {
                    SharedPref.write(SharedPref.BOOK_DARK_MODE, false)
                }
                dataChanged()
            }
        }

        // Realization Change Font Size
        binding.apply {
            var currentFontSize = SharedPref.read(SharedPref.BOOK_FONT_SIZE, FONT_SIZE_NORMAL)
            tvCurrentFontSize.text =
                when (currentFontSize) {
                FONT_SIZE_LARGE -> getString(R.string.large)

                FONT_SIZE_SMALL -> getString(R.string.small)

                else -> getString(R.string.normal)
            }
            fontSizeMinus.setOnClickListener{
                when (currentFontSize) {
                    FONT_SIZE_LARGE -> {
                        tvCurrentFontSize.text = getString(R.string.normal)
                        currentFontSize = FONT_SIZE_NORMAL
                    }
                    FONT_SIZE_NORMAL -> {
                        tvCurrentFontSize.text = getString(R.string.small)
                        currentFontSize = FONT_SIZE_SMALL
                    }
                }
                SharedPref.write(SharedPref.BOOK_FONT_SIZE,currentFontSize)
                dataChanged()
            }
            fontSizePlus.setOnClickListener{
                when (currentFontSize) {
                    FONT_SIZE_SMALL -> {
                        tvCurrentFontSize.text = getString(R.string.normal)
                        currentFontSize = FONT_SIZE_NORMAL
                    }
                    FONT_SIZE_NORMAL -> {
                        tvCurrentFontSize.text = getString(R.string.large)
                        currentFontSize = FONT_SIZE_LARGE
                    }
                }
                SharedPref.write(SharedPref.BOOK_FONT_SIZE,currentFontSize)
                dataChanged()
            }
        }

        // Realization Save Book
        val bookID = requireArguments().getString(Books.BOOK_ID_KEY,"0")
        binding.apply {
            toggleLike.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    SharedPref.write(bookID, true)
                }else {
                    SharedPref.write(bookID, false)
                }
            }
            toggleLike.isChecked = SharedPref.read(bookID, false)
            toggleLike.setOnClickListener {
                var favoriteBooks = SharedPref.read(SharedPref.FAVORITE_BOOKS,0)
                if (toggleLike.isChecked) {
                    favoriteBooks++
                    SharedPref.write(
                        SharedPref.FAVORITE_BOOKS,
                        favoriteBooks)
                }else {
                    favoriteBooks--
                    SharedPref.write(SharedPref.FAVORITE_BOOKS,
                        favoriteBooks)
                }
            }
        }

        // Realization Navigate to Words
        binding.lSavedWords.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetFragment_to_wordsFragment)
        }
        binding.numberOfWords.text = SharedPref.read(SharedPref.SELECTED_WORDS, 0).toString()




        return view
    }

    private fun dataChanged(){
        findNavController().previousBackStackEntry?.savedStateHandle?.set(DATA_CHANGED,true)
    }

    companion object{
        const val DATA_CHANGED = "DATA_CHANGED"
        const val FONT_SIZE_NORMAL = "FONT_SIZE_NORMAL"
        const val FONT_SIZE_SMALL = "FONT_SIZE_SMALL"
        const val FONT_SIZE_LARGE = "FONT_SIZE_BIG"
    }
}