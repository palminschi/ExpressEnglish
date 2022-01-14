package com.palmdev.expressenglish.fragments.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentGameWriteWordBinding
import com.palmdev.expressenglish.fragments.GroupOfWordsFragment

class GameWriteWordFragment : Fragment(R.layout.fragment_game_write_word) {

    private lateinit var binding: FragmentGameWriteWordBinding
    private var mCurrentWordCounter = 0
    private val mArrayWords = ArrayList<String>()
    private val mArrayTranslatedWords = ArrayList<String>()
    private val mArrayPhrases = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameWriteWordBinding.bind(view)

        // Get data from arguments
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_WORDS)?.let {
            mArrayWords.addAll( it )
        }
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_TRANSLATED_WORDS)?.let {
            mArrayTranslatedWords.addAll( it )
        }
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_PHRASES)?.let {
            mArrayPhrases.addAll( it )
        }


    }


    private fun updateContent(){
        /* binding.tvWord.text = mArrayWords[mCurrentWordCounter]
         binding.tvTranslatedWord.text = mArrayTranslatedWords[mCurrentWordCounter]
         binding.tvPhrase.text = mArrayPhrases[mCurrentWordCounter]*/

    }
}