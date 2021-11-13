package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentExamsBinding

class ExamsFragment: Fragment(R.layout.fragment_exams) {

    private lateinit var binding: FragmentExamsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExamsBinding.bind(view)

    }
}