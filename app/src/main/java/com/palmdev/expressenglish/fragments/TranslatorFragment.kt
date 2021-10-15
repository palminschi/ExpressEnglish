package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentTranslatorBinding

class TranslatorFragment: Fragment(R.layout.fragment_translator) {

    private lateinit var binding: FragmentTranslatorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTranslatorBinding.bind(view)

    }
}