package com.palmdev.expressenglish.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.GroupOfWordsAdapter
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentWordsBinding
import com.palmdev.expressenglish.models.GroupOfWords
import com.palmdev.expressenglish.utils.TextToClickable
import kotlin.math.ceil

class WordsFragment : Fragment(R.layout.fragment_words) {

    private lateinit var binding: FragmentWordsBinding
    private val mAdapter = GroupOfWordsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWordsBinding.bind(view)


        binding.recView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recView.adapter = mAdapter
        if (mAdapter.itemCount == 0) {
            initRecyclerView()
        }
        // If the user has no words yet
        if (mAdapter.itemCount == 0) {
            binding.apply {
                recView.visibility = View.INVISIBLE
                noData.visibility = View.VISIBLE
                readBooks.setOnClickListener {
                    findNavController().navigate(R.id.booksFragment)
                }
                learnRandomWords.visibility = View.INVISIBLE
            }

        }


    }


    @SuppressLint("ClickableViewAccessibility")

    private fun initRecyclerView() {
        val wordsFromPref = SharedPref.getArray(SharedPref.WORDS_ARRAY)
        //val translatedWordsFromPref = SharedPref.readArray(SharedPref.TRANSLATED_WORDS_ARRAY)
        val numberOfGroups = ceil(wordsFromPref.size.toDouble() / 10).toInt()

        for (i in 0 until numberOfGroups) {
            val firstWordIndex = i * 10
            var lastWordIndex = firstWordIndex + 9
            if (lastWordIndex >= wordsFromPref.size) lastWordIndex = wordsFromPref.size - 1

            var numberOfWordsInGroup = lastWordIndex + firstWordIndex + 1
            if (numberOfWordsInGroup > 10) {
                numberOfWordsInGroup = numberOfWordsInGroup.toString().takeLast(1).toInt()
                if (numberOfWordsInGroup == 0) numberOfWordsInGroup = 10
            }
            val group = GroupOfWords(i + 1, numberOfWordsInGroup, firstWordIndex, lastWordIndex)
            mAdapter.addGroup(group)
        }
    }
}