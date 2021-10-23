package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentGroupOfWordsBinding

class GroupOfWordsFragment : Fragment(R.layout.fragment_group_of_words) {

    lateinit var binding: FragmentGroupOfWordsBinding
    private lateinit var mCurrentWords: MutableList<String>
    private lateinit var mCurrentTranslatedWords: MutableList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGroupOfWordsBinding.bind(view)

        val firstWordIndex = requireArguments().getInt(FIRST_INDEX)
        val lastWordIndex = requireArguments().getInt(LAST_INDEX)
        val wordsFromPref = SharedPref.getArray(SharedPref.WORDS_ARRAY)
        val translatedWordsFromPref = SharedPref.getArray(SharedPref.TRANSLATED_WORDS_ARRAY)

        mCurrentWords = wordsFromPref.subList(firstWordIndex,lastWordIndex + 1)
        mCurrentTranslatedWords = translatedWordsFromPref.subList(firstWordIndex,lastWordIndex + 1)

        initContent()
    }

    private fun initContent() = with(binding) {
        val linesViews = listOf(
            line01,
            line02,
            line03,
            line04,
            line05,
            line06,
            line07,
            line08,
            line09,
            line10,
        )
        val wordsViews = listOf(
            word01,
            word02,
            word03,
            word04,
            word05,
            word06,
            word07,
            word08,
            word09,
            word10,
        )
        val translatedWordsViews = listOf(
            translation01,
            translation02,
            translation03,
            translation04,
            translation05,
            translation06,
            translation07,
            translation08,
            translation09,
            translation10,
        )

        for (i in 0 until mCurrentWords.size) {
            linesViews[i].visibility = View.VISIBLE
            wordsViews[i].text = mCurrentWords[i]
            translatedWordsViews[i].text = mCurrentTranslatedWords[i]
        }
    }

    companion object{
        const val FIRST_INDEX = "FIRST_INDEX"
        const val LAST_INDEX = "LAST_INDEX"
    }
}