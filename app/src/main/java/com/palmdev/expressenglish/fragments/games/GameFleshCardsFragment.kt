package com.palmdev.expressenglish.fragments.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentGameFleshCardsBinding
import com.palmdev.expressenglish.fragments.GroupOfWordsFragment
import com.palmdev.expressenglish.utils.MyTextToSpeech


class GameFleshCardsFragment : Fragment(R.layout.fragment_game_flesh_cards) {

    private lateinit var binding: FragmentGameFleshCardsBinding
    private var mCurrentWordCounter = 0
    private val mArrayWords = ArrayList<String>()
    private val mArrayTranslatedWords = ArrayList<String>()
    private val mArrayPhrases = ArrayList<String>()
    private lateinit var mCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameFleshCardsBinding.bind(view)

        // Get data from arguments
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_WORDS)?.let {
            mArrayWords.addAll(it)
        }
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_TRANSLATED_WORDS)?.let {
            mArrayTranslatedWords.addAll(it)
        }
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_PHRASES)?.let {
            mArrayPhrases.addAll(it)
        }

        updateContent()

        binding.btnNext.setOnClickListener {
            when (binding.tvBtnNext.text) {
                getString(R.string.show) -> {
                    mCurrentWordCounter++
                    showWord()
                }
                getString(R.string.next) -> {
                    updateContent()
                }
                getString(R.string.done) -> {
                    findNavController().navigateUp()
                    findNavController().navigateUp()
                }
            }
        }

        binding.btnSound.setOnClickListener {
            MyTextToSpeech.play(binding.tvWord.text.toString(),requireContext())
        }

    }

    private fun updateContent() {
        binding.tvWord.text = mArrayWords[mCurrentWordCounter]
        binding.tvTranslatedWord.text = mArrayTranslatedWords[mCurrentWordCounter]
        binding.tvPhrase.text = mArrayPhrases[mCurrentWordCounter]
        hideWord()
        // if the word has no sentences
        if (mArrayPhrases[mCurrentWordCounter] == "empty") {
            binding.tvPhrase.visibility = View.GONE
        }else {
            binding.tvPhrase.visibility = View.VISIBLE
        }
        binding.tvBtnNext.text = getString(R.string.show)

        MyTextToSpeech.play(binding.tvWord.text.toString(),requireContext())
    }

    private fun showWord() {
        binding.hiddenText.visibility = View.GONE
        binding.tvTranslatedWord.visibility = View.VISIBLE

        binding.tvBtnNext.text =
                // if it isn't last word
            if (mCurrentWordCounter != mArrayWords.size) {
                getString(R.string.next)
            } else getString(R.string.done)
    }

    private fun hideWord() {
        binding.tvTranslatedWord.visibility = View.GONE
        binding.hiddenText.visibility = View.VISIBLE
    }

    private fun setOnBackPressedCallback(){
        mCallback = object :  OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }

    override fun onResume() {
        super.onResume()
        setOnBackPressedCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCallback.remove()
    }

}