package com.palmdev.expressenglish.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.BookAdapter
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var mBinding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentHomeBinding.bind(view)

        initButtons()

    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    @SuppressLint("SetTextI18n")
    private fun initData() = with(mBinding){
        val favoriteBooks = SharedPref.read(SharedPref.FAVORITE_BOOKS,0)
        tvFavoriteBooks.text = getString(R.string.favoriteBooks) + " " + favoriteBooks
        val selectedWords = SharedPref.read(SharedPref.SELECTED_WORDS,0)
        tvSelectedWords.text = getString(R.string.selectedWords) + " " + selectedWords
        val learnedTopics = SharedPref.read(SharedPref.LEARNED_TOPICS,0)
        tvLearnedTopics.text = getString(R.string.learnedTopics) + " " + learnedTopics
        val examsPassed = SharedPref.read(SharedPref.EXAMS_PASSED,0)
        tvExamsPassed.text = getString(R.string.examsPassed) + " " + examsPassed

    }
    private fun initButtons() = with(mBinding){
        btnBooks.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_booksFragment)
        }
        btnGrammar.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_grammarFragment)
        }
        btnExams.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_examsFragment)
        }
        btnWords.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_wordsFragment)
        }
        btnTest.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quickTestFragment)
        }
        btnTranslator.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_translatorFragment)
        }
    }


}