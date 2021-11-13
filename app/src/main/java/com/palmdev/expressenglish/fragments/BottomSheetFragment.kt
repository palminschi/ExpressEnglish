package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentBottomSheetBinding
import com.palmdev.expressenglish.utils.AllLanguages


class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.lLangSettings.setOnClickListener {
            val dialog = Dialogs.dialogSelectLanguage(requireContext())
            dialog.show()
        }
        val userTranslatorLang = SharedPref.get(SharedPref.USER_TRANSLATOR_LANGUAGE_CODE,"?")
        AllLanguages.initLanguages()
        binding.tvLanguage.text = AllLanguages.allLanguagesName[AllLanguages
            .allLanguagesCode.indexOf(userTranslatorLang)]

        // Realization the Button Switch Theme
        binding.apply {
            switchTheme.isChecked = SharedPref.get(SharedPref.BOOK_DARK_MODE, false)
            switchTheme.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    SharedPref.put(SharedPref.BOOK_DARK_MODE, true)
                } else {
                    SharedPref.put(SharedPref.BOOK_DARK_MODE, false)
                }
                dataChanged()
            }
        }

        // Realization Change Font Size
        binding.apply {
            var currentFontSize = SharedPref.get(SharedPref.BOOK_FONT_SIZE, FONT_SIZE_NORMAL)
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
                SharedPref.put(SharedPref.BOOK_FONT_SIZE,currentFontSize)
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
                SharedPref.put(SharedPref.BOOK_FONT_SIZE,currentFontSize)
                dataChanged()
            }
        }

        // Realization Save Book
        val bookID = requireArguments().getString(Books.BOOK_ID_KEY,"0")
        binding.apply {
            toggleLike.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    SharedPref.put(bookID, true)
                }else {
                    SharedPref.put(bookID, false)
                }
            }
            toggleLike.isChecked = SharedPref.get(bookID, false)
            toggleLike.setOnClickListener {
                var favoriteBooks = SharedPref.get(SharedPref.FAVORITE_BOOKS,0)
                if (toggleLike.isChecked) {
                    favoriteBooks++
                    SharedPref.put(
                        SharedPref.FAVORITE_BOOKS,
                        favoriteBooks)
                }else {
                    favoriteBooks--
                    SharedPref.put(SharedPref.FAVORITE_BOOKS,
                        favoriteBooks)
                }
            }
        }

        // Realization Navigate to Words
        binding.lSavedWords.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetFragment_to_wordsFragment)
        }
        binding.numberOfSelectedWords.text =
            SharedPref.get(SharedPref.SELECTED_WORDS, 0).toString()




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