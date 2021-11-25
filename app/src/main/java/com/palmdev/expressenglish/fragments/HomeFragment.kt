package com.palmdev.expressenglish.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentHomeBinding
import kotlin.system.exitProcess

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var mBinding: FragmentHomeBinding
    private var mBackPressedTime: Long = 0
    private lateinit var mCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentHomeBinding.bind(view)

        initButtons()
        setOnBackPressedCallback()

    }


    override fun onPause() {
        mCallback.remove()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        initData()
    }



    @SuppressLint("SetTextI18n")
    private fun initData() = with(mBinding) {
        tvFavoriteBooks.text = getString(R.string.favoriteBooks) + " " + User.getFavoriteBooks()
        tvSelectedWords.text = getString(R.string.selectedWords) + " " + User.getSelectedWords()
        tvLearnedTopics.text = getString(R.string.learnedTopics) + " " + User.getLearnedTopics()
        tvExamsPassed.text = getString(R.string.examsPassed) + " " + User.getExamsPassed()

        val userName = User.getName(requireContext())
        tvHelloUser.text = "${getString(R.string.hello)}, $userName!"

        val userLvl = User.getLevel(requireContext())
        tvUserLvl.text = userLvl

        if (userLvl == getString(R.string.unknownLvl)) {
            tvUserLvl.visibility = View.GONE
            btnTest.visibility = View.VISIBLE
        }else {
            tvUserLvl.visibility = View.VISIBLE
            btnTest.visibility = View.GONE
        }

        imgUserAvatar.setImageResource(User.getAvatar(requireContext()))
    }


    private fun initButtons() = with(mBinding) {
        btnBooks.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_booksFragment)
        }
        btnGrammar.setOnClickListener {
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

    private fun setOnBackPressedCallback(){
        mCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mBackPressedTime + 2000 > System.currentTimeMillis()) {
                    activity?.moveTaskToBack(true)
                    exitProcess(0)
                } else {
                    Toast.makeText(
                        requireContext(), getString(R.string.toastExitApp), Toast.LENGTH_SHORT
                    ).show()
                }
                mBackPressedTime = System.currentTimeMillis()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }


}