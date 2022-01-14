package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentGroupOfWordsBinding
import kotlin.random.Random

class GroupOfWordsFragment : Fragment(R.layout.fragment_group_of_words) {

    private lateinit var binding: FragmentGroupOfWordsBinding
    private var mCurrentWords = ArrayList<String>()
    private var mCurrentTranslatedWords = ArrayList<String>()
    private var mCurrentPhrases = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGroupOfWordsBinding.bind(view)

        setWords()
        initContent()

        binding.btnGameFleshCards.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupOfWordsFragment_to_gameFleshCardsFragment,
                bundleOf(
                    ARRAY_WORDS to mCurrentWords,
                    ARRAY_TRANSLATED_WORDS to mCurrentTranslatedWords,
                    ARRAY_PHRASES to mCurrentPhrases
                )
            )
        }
        binding.btnGameChoice.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupOfWordsFragment_to_gameSelectWordFragment,
                bundleOf(
                    ARRAY_WORDS to mCurrentWords,
                    ARRAY_TRANSLATED_WORDS to mCurrentTranslatedWords,
                    ARRAY_PHRASES to mCurrentPhrases
                )
            )
        }
        binding.btnGameWrite.setOnClickListener {
            // TODO: If is Premium
            findNavController().navigate(
                R.id.action_groupOfWordsFragment_to_gameWriteWordFragment,
                bundleOf(
                    ARRAY_WORDS to mCurrentWords,
                    ARRAY_TRANSLATED_WORDS to mCurrentTranslatedWords,
                    ARRAY_PHRASES to mCurrentPhrases
                )
            )
        }
    }

    private fun setWords(){
        val indexOfFirstWord = requireArguments().getInt(FIRST_INDEX)
        val indexOfLastWord = requireArguments().getInt(LAST_INDEX)
        val wordsFromPref = SharedPref.getArray(SharedPref.WORDS_ARRAY)
        val translatedWordsFromPref = SharedPref.getArray(SharedPref.TRANSLATED_WORDS_ARRAY)
        val phrasesFromPref = SharedPref.getArray(SharedPref.PHRASES_ARRAY)

        // If a random option was chosen
        if (indexOfFirstWord == 999 && indexOfLastWord == 999) {
            val randomNumbers = getTenRandomNumbers(wordsFromPref.size)
            for (i in 0 until randomNumbers.size) {
                mCurrentWords.add(wordsFromPref[randomNumbers[i]])
                mCurrentTranslatedWords.add(translatedWordsFromPref[randomNumbers[i]])
                mCurrentPhrases.add(phrasesFromPref[randomNumbers[i]])
            }
        } else {
            if (indexOfFirstWord == indexOfLastWord) {
                mCurrentWords.add(wordsFromPref[indexOfFirstWord])
                mCurrentTranslatedWords.add(translatedWordsFromPref[indexOfFirstWord])
                mCurrentPhrases.add(phrasesFromPref[indexOfFirstWord])
            }else {
                mCurrentWords =
                    wordsFromPref.subList(indexOfFirstWord, indexOfLastWord + 1)
                        .toList() as ArrayList<String>
                mCurrentTranslatedWords =
                    translatedWordsFromPref.subList(indexOfFirstWord, indexOfLastWord + 1)
                        .toList() as ArrayList<String>
                mCurrentPhrases =
                    phrasesFromPref.subList(indexOfFirstWord, indexOfLastWord + 1)
                        .toList() as ArrayList<String>
            }
        }
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

    private fun getTenRandomNumbers(maxNumber: Int): ArrayList<Int>{
        val randomNumbers = ArrayList<Int>()
        fun getRandom(): Int {
            return Random.nextInt(maxNumber)
        }

        val number1 = getRandom()
        var number2 = getRandom()
        while (number2 == number1) number2 = getRandom()
        var number3 = getRandom()
        while (number3 == number2 || number3 == number1) number3 = getRandom()
        var number4 = getRandom()
        while (number4 == number3 || number4 == number2 || number4 == number1) {
            number4 = getRandom()
        }
        var number5 = getRandom()
        while (number5 == number4 || number5 == number3 || number5 == number2
            || number5 == number1
        ) number5 = getRandom()
        var number6 = getRandom()
        while (number6 == number5 || number6 == number4 || number6 == number3
            || number6 == number2 || number6 == number1
        ) number6 = getRandom()
        var number7 = getRandom()
        while (number7 == number6 || number7 == number5 || number7 == number4 || number7
            == number3 || number7 == number2 || number7 == number1
        ) number7 = getRandom()
        var number8 = getRandom()
        while (number8 == number7 || number8 == number6 || number8 == number5
            || number8 == number4 || number8 == number3 || number8 == number2
            || number8 == number1
        ) number8 = getRandom()
        var number9 = getRandom()
        while (number9 == number7 || number9 == number6 || number9 == number5
            || number9 == number4 || number9 == number3 || number9 == number2
            || number9 == number1 || number9 == number8
        ) number9 = getRandom()
        var number10 = getRandom()
        while (number10 == number7 || number10 == number6 || number10 == number5
            || number10 == number4 || number10 == number3 || number10 == number2
            || number10 == number1 || number10 == number8 || number10 == number9
        ) number10 = getRandom()

        randomNumbers.add(number1)
        randomNumbers.add(number2)
        randomNumbers.add(number3)
        randomNumbers.add(number4)
        randomNumbers.add(number5)
        randomNumbers.add(number6)
        randomNumbers.add(number7)
        randomNumbers.add(number8)
        randomNumbers.add(number9)
        randomNumbers.add(number10)

        return randomNumbers
    }

    companion object {
        const val FIRST_INDEX = "FIRST_INDEX"
        const val LAST_INDEX = "LAST_INDEX"

        const val ARRAY_WORDS = "ARRAY_WORDS"
        const val ARRAY_TRANSLATED_WORDS = "ARRAY_TRANSLATED_WORDS"
        const val ARRAY_PHRASES = "ARRAY_PHRASES"
    }
}