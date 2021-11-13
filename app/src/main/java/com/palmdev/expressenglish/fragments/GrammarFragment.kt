package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentGrammarBinding

class GrammarFragment: Fragment(R.layout.fragment_grammar) {

    private lateinit var binding: FragmentGrammarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGrammarBinding.bind(view)

        binding.apply {
            btnTest.setOnClickListener {
                findNavController().navigate(R.id.action_grammarFragment_to_quickTestFragment)
            }
            btnExams.setOnClickListener {
                findNavController().navigate(R.id.action_grammarFragment_to_examsFragment)
            }
            btnA1.setOnClickListener {
                findNavController().navigate(R.id.action_grammarFragment_to_selectLessonFragment,
                bundleOf(SELECTED_LEVEL to A1_SELECTED))
            }
            btnA2.setOnClickListener {
                findNavController().navigate(R.id.action_grammarFragment_to_selectLessonFragment,
                bundleOf(SELECTED_LEVEL to A2_SELECTED))
            }
            btnB1.setOnClickListener {
                findNavController().navigate(R.id.action_grammarFragment_to_selectLessonFragment,
                bundleOf(SELECTED_LEVEL to B1_SELECTED))
            }
        }

    }

    companion object{
        const val SELECTED_LEVEL = "SELECTED_LEVEL"
        const val A1_SELECTED = "A1_SELECTED"
        const val A2_SELECTED = "A2_SELECTED"
        const val B1_SELECTED = "B1_SELECTED"
    }
}