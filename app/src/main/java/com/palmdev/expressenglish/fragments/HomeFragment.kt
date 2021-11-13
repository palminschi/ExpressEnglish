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

        // init Callback to close the app with double tap
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
        val favoriteBooks = SharedPref.get(SharedPref.FAVORITE_BOOKS, 0)
        tvFavoriteBooks.text = getString(R.string.favoriteBooks) + " " + favoriteBooks
        val selectedWords = SharedPref.get(SharedPref.SELECTED_WORDS, 0)
        tvSelectedWords.text = getString(R.string.selectedWords) + " " + selectedWords
        val learnedTopics = SharedPref.get(SharedPref.LEARNED_TOPICS, 0)
        tvLearnedTopics.text = getString(R.string.learnedTopics) + " " + learnedTopics
        val examsPassed = SharedPref.get(SharedPref.EXAMS_PASSED, 0)
        tvExamsPassed.text = getString(R.string.examsPassed) + " " + examsPassed

        val userName = SharedPref.get(SharedPref.USER_NAME, "learner")
        tvHelloUser.text = "Hello, $userName!"
        val userLvl = SharedPref.get(SharedPref.USER_LEVEL, getString(R.string.unknownLvl))
        tvUserLvl.text = userLvl
        if (userLvl == getString(R.string.unknownLvl)) {
            tvUserLvl.visibility = View.GONE
            btnTest.visibility = View.VISIBLE
        }else {
            tvUserLvl.visibility = View.VISIBLE
            btnTest.visibility = View.GONE
        }
        val userGender = SharedPref.get(SharedPref.USER_GENDER, getString(R.string.man))

        setAvatar(userGender,userLvl)
    }

    private fun setAvatar( userGender:String?, userLevel:String? ) = with(mBinding) {
        if (userGender == getString(R.string.woman)){
            when (userLevel) {
                getString(R.string.A1Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_w_a1)
                getString(R.string.A2Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_w_a2)
                getString(R.string.B1Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_w_b1)
                getString(R.string.B2Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_w_b2)
                getString(R.string.C1Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_w_c1)
                getString(R.string.C2Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_w_c2)
                getString(R.string.unknownLvl) ->
                    imgUserAvatar.setImageResource(R.drawable.avatar_w_b1)
            }
        }else{
            when (userLevel) {
                getString(R.string.A1Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_m_a1)
                getString(R.string.A2Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_m_a2)
                getString(R.string.B1Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_m_b1)
                getString(R.string.B2Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_m_b2)
                getString(R.string.C1Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_m_c1)
                getString(R.string.C2Lvl) -> imgUserAvatar.setImageResource(R.drawable.avatar_m_c2)
                getString(R.string.unknownLvl) ->
                    imgUserAvatar.setImageResource(R.drawable.avatar_m_b1)
            }
        }
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


}