package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentQuickTestBinding

class QuickTestFragment: Fragment(R.layout.fragment_quick_test) {

    private lateinit var binding: FragmentQuickTestBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuickTestBinding.bind(view)

    }
}