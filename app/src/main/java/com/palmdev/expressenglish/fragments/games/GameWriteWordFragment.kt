package com.palmdev.expressenglish.fragments.games

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentGameWriteWordBinding
import com.palmdev.expressenglish.fragments.GroupOfWordsFragment
import com.palmdev.expressenglish.utils.MyTextToSpeech
import java.util.*
import kotlin.collections.ArrayList

class GameWriteWordFragment : Fragment(R.layout.fragment_game_write_word) {

    private lateinit var binding: FragmentGameWriteWordBinding
    private var mCurrentWordCounter = 0
    private val mArrayWords = ArrayList<String>()
    private val mArrayTranslatedWords = ArrayList<String>()
    private val mArrayPhrases = ArrayList<String>()
    private lateinit var mCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameWriteWordBinding.bind(view)

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
                getString(R.string.check) -> {
                    checkAnswer()
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
    }


    private fun updateContent() {
        binding.tvTranslatedWord.text = mArrayTranslatedWords[mCurrentWordCounter]
        if (mArrayPhrases[mCurrentWordCounter] != "empty") {
            binding.tvPhrase.text = mArrayPhrases[mCurrentWordCounter]
        }
        binding.tvPhrase.visibility = View.GONE
        binding.editText.setText("")
        binding.editText.setTextColor(Color.BLACK)
        binding.tvBtnNext.text = getText(R.string.check)

    }

    private fun checkAnswer() {
        val rightAnswer = mArrayWords[mCurrentWordCounter].lowercase(Locale.getDefault())
        val userAnswer = binding.editText.text.toString().lowercase(Locale.getDefault())

        binding.editText.setText(mArrayWords[mCurrentWordCounter])

        if (userAnswer == rightAnswer) {
            binding.editText.setTextColor(resources.getColor(R.color.green))
        } else binding.editText.setTextColor(Color.RED)

        binding.tvPhrase.visibility = View.VISIBLE
        MyTextToSpeech.play(mArrayWords[mCurrentWordCounter], requireContext())

        mCurrentWordCounter++

        binding.tvBtnNext.text =
                // if it isn't last word
            if (mCurrentWordCounter != mArrayWords.size) {
                getString(R.string.next)
            } else getString(R.string.done)


    }


    private fun setOnBackPressedCallback() {
        mCallback = object : OnBackPressedCallback(true) {
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