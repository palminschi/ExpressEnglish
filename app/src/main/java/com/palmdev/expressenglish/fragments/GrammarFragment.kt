package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentGrammarBinding

class GrammarFragment: Fragment(R.layout.fragment_grammar) {

    private lateinit var binding: FragmentGrammarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGrammarBinding.bind(view)

    }
}