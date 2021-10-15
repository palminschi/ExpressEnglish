package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentWordsBinding

class WordsFragment: Fragment(R.layout.fragment_words) {

    private lateinit var binding: FragmentWordsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWordsBinding.bind(view)

    }
}