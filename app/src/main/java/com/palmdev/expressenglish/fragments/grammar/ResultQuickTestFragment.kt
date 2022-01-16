package com.palmdev.expressenglish.fragments.grammar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.TestsAdapter
import com.palmdev.expressenglish.data.Tests
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentResultQuickTestBinding

class ResultQuickTestFragment: Fragment(R.layout.fragment_result_quick_test) {

    private lateinit var binding: FragmentResultQuickTestBinding
    private lateinit var mCallback: OnBackPressedCallback
    private var mCorrectAnswers: Int = 0
    private var mTotalQuestions: Int = 0
    private lateinit var mIncorrectAnswersArray: ArrayList<String>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultQuickTestBinding.bind(view)

        mCorrectAnswers = requireArguments().getInt(TestsFragment.CORRECT_ANSWERS)
        mTotalQuestions = requireArguments().getInt(TestsFragment.TOTAL_QUESTIONS)
        mIncorrectAnswersArray =
            requireArguments()
                .getStringArrayList(TestsFragment.INCORRECT_ANSWERS_ARRAY) as ArrayList<String>

        // Set Content
        initTestDetails()
        initButtonDetails()
        setUserLevel()

        binding.btnExit.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
        }
        binding.btnRestart.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().navigate(
                R.id.testsFragment,
                bundleOf(Tests.EXAM_OR_QUICK_TEST to Tests.QUICK_TEST))
        }


    }

    private fun setUserLevel(){

        val userGender = User.getGender(requireContext())
        val woman = getString(R.string.woman)
        val man = getString(R.string.man)
        val userLvl = User.getLevel(requireContext())
        val unknownLvl = getString(R.string.unknownLvl)
        val a1 = getString(R.string.A1Lvl)
        val a2 = getString(R.string.A2Lvl)
        val b1 = getString(R.string.B1Lvl)
        val b2 = getString(R.string.B2Lvl)
        val c1 = getString(R.string.C1Lvl)
        val c2 = getString(R.string.C2Lvl)


        when (mCorrectAnswers) {
            // A1 or less
            in 0..3 -> {
                binding.tvReceivedLvl.text = a1
                binding.tvReceivedLvlDetails.text = getString(R.string.orLess)
                if (userGender == woman) {
                    binding.imageView.setImageResource(R.drawable.avatar_w_a1)
                }else {
                    binding.imageView.setImageResource(R.drawable.avatar_m_a1)
                }
                if (userLvl == unknownLvl) {
                    User.setLevel(a1)
                }
            }
            // A1
            in 4..6 -> {
                binding.tvReceivedLvl.text = a1
                binding.tvReceivedLvlDetails.visibility = View.GONE
                if (userGender == woman) {
                    binding.imageView.setImageResource(R.drawable.avatar_w_a1)
                }else {
                    binding.imageView.setImageResource(R.drawable.avatar_m_a1)
                }
                if (userLvl == unknownLvl) {
                    User.setLevel(a1)
                }
            }
            // A2
            in 7..11 -> {
                binding.tvReceivedLvl.text = a2
                binding.tvReceivedLvlDetails.visibility = View.GONE
                if (userGender == woman) {
                    binding.imageView.setImageResource(R.drawable.avatar_w_a2)
                }else {
                    binding.imageView.setImageResource(R.drawable.avatar_m_a2)
                }
                if (userLvl == unknownLvl || userLvl == a1) {
                    User.setLevel(a2)
                }
            }
            // B1 or more
            in 12..14 -> {
                binding.tvReceivedLvl.text = b1
                binding.tvReceivedLvlDetails.text = getString(R.string.orMore)
                if (userGender == woman) {
                    binding.imageView.setImageResource(R.drawable.avatar_w_b1)
                }else {
                    binding.imageView.setImageResource(R.drawable.avatar_m_b1)
                }
                if (userLvl == unknownLvl || userLvl == a1 || userLvl == a2) {
                    User.setLevel(b1)
                }
            }
            // B2 or more
            15 -> {
                binding.tvReceivedLvl.text = b2
                binding.tvReceivedLvlDetails.text = getString(R.string.orMore)
                if (userGender == woman) {
                    binding.imageView.setImageResource(R.drawable.avatar_w_b2)
                }else {
                    binding.imageView.setImageResource(R.drawable.avatar_m_b2)
                }
                if (userLvl == unknownLvl || userLvl == a1 || userLvl == a2 || userLvl == b1) {
                    User.setLevel(b2)
                }
            }
        }
    }

    private fun initButtonDetails(){
        binding.btnDetails.setOnClickListener {
            // TODO: if is premium
            if (binding.rightAnswered.isVisible){
                binding.listOfIncorrectAnswers.visibility = View.GONE
                binding.detailsSubTitle.visibility = View.GONE
                binding.rightAnswered.visibility = View.GONE
                binding.ivExpandMore.rotation = 360f
            }else {
                binding.rightAnswered.visibility = View.VISIBLE
                binding.ivExpandMore.rotation = 180f
                // If there aren't incorrect answers
                if (mIncorrectAnswersArray.isEmpty()){
                    binding.listOfIncorrectAnswers.visibility = View.GONE
                    binding.detailsSubTitle.visibility = View.GONE
                }else {
                    binding.listOfIncorrectAnswers.visibility = View.VISIBLE
                    binding.detailsSubTitle.visibility = View.VISIBLE
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initTestDetails(){
        binding.rightAnswered.text =
            getString(R.string.correctAnswered) + " $mCorrectAnswers / $mTotalQuestions"
        binding.detailsSubTitle.visibility = View.GONE
        binding.listOfIncorrectAnswers.visibility = View.GONE
        binding.rightAnswered.visibility = View.GONE

        // Convert Array into String
        if (mIncorrectAnswersArray.isNotEmpty()){
            binding.listOfIncorrectAnswers.text = mIncorrectAnswersArray.joinToString(
                separator = "\n"
            )
        }
    }

    private fun clearOldData(){
        TestsAdapter.incorrectAnswersArray.clear()
        TestsAdapter.correctAnswers = 0
    }

    private fun setOnBackPressedCallback(){
        mCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
                findNavController().popBackStack()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }

    override fun onPause() {
        super.onPause()
        clearOldData()
    }

    override fun onResume() {
        super.onResume()
        setOnBackPressedCallback()
    }
    override fun onDestroy() {
        super.onDestroy()
        mCallback.remove()
    }
}