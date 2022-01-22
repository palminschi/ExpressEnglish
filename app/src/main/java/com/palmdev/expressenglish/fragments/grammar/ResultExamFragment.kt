package com.palmdev.expressenglish.fragments.grammar

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardItem
import com.palmdev.expressenglish.Ads
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.TestsAdapter
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.Tests
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentResultExamBinding

class ResultExamFragment : Fragment(R.layout.fragment_result_exam) {

    private lateinit var binding: FragmentResultExamBinding
    private lateinit var mCallback: OnBackPressedCallback
    private var mCorrectAnswers: Int = 0
    private var mTotalQuestions: Int = 0
    private lateinit var mIncorrectAnswersArray: ArrayList<String>
    private lateinit var mExamID: String
    private lateinit var mExamLevel: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultExamBinding.bind(view)

        mExamID = requireArguments().getString(Tests.EXAM, "")
        mExamLevel = requireArguments().getString(Tests.LEVEL, "")
        mCorrectAnswers = requireArguments().getInt(TestsFragment.CORRECT_ANSWERS)
        mTotalQuestions = requireArguments().getInt(TestsFragment.TOTAL_QUESTIONS)
        mIncorrectAnswersArray =
            requireArguments()
                .getStringArrayList(TestsFragment.INCORRECT_ANSWERS_ARRAY) as ArrayList<String>

        // Set Content
        initButtonDetails()
        initTestDetails()
        successOrFail()
        setExamName()

        binding.btnExit.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
        }

        binding.btnRestart.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().navigate(
                R.id.testsFragment, bundleOf(
                    Tests.EXAM_OR_QUICK_TEST to Tests.EXAM,
                    Tests.EXAM to mExamID
                )
            )
        }
    }


    private fun successOrFail() {
        val mistakes = mTotalQuestions - mCorrectAnswers
        val maxAllowedMistakes = 4

        if (mistakes > maxAllowedMistakes) {
            binding.imageView.setImageResource(R.drawable.icon_failure)
            binding.tvSuccessOrFail.text = getText(R.string.failure)
            binding.tvSuccessOrFail.setTextColor(resources.getColor(R.color.red))
            binding.subTitle.text = getText(R.string.examFailed)
            binding.btnRestart.visibility = View.VISIBLE
        } else {
            binding.imageView.setImageResource(R.drawable.icon_success)
            binding.tvSuccessOrFail.text = getText(R.string.success)
            binding.tvSuccessOrFail.setTextColor(resources.getColor(R.color.green))
            binding.subTitle.text = getText(R.string.examPassedSuccessfully)
            binding.btnRestart.visibility = View.INVISIBLE
            // Save that exam was passed in SharedPref
            SharedPref.put(mExamID, true)
            User.addExamsPassed()
            User.setLevel(requireContext(), mExamLevel)
        }
    }


    private fun setExamName() {
        val examName = when (mExamID) {
            Tests.EXAM_A1_N1 -> "${getText(R.string.A1Lvl)} - ${getText(R.string.examN1)}"
            Tests.EXAM_A1_N2 -> "${getText(R.string.A1Lvl)} - ${getText(R.string.examN2)}"
            Tests.EXAM_A1_N3 -> "${getText(R.string.A1Lvl)} - ${getText(R.string.examN3)}"
            Tests.EXAM_A2_N1 -> "${getText(R.string.A2Lvl)} - ${getText(R.string.examN1)}"
            Tests.EXAM_A2_N2 -> "${getText(R.string.A2Lvl)} - ${getText(R.string.examN2)}"
            Tests.EXAM_A2_N3 -> "${getText(R.string.A2Lvl)} - ${getText(R.string.examN3)}"
            Tests.EXAM_B1_N1 -> "${getText(R.string.B1Lvl)} - ${getText(R.string.examN1)}"
            Tests.EXAM_B1_N2 -> "${getText(R.string.B1Lvl)} - ${getText(R.string.examN2)}"
            Tests.EXAM_B1_N3 -> "${getText(R.string.B1Lvl)} - ${getText(R.string.examN3)}"
            Tests.EXAM_B2_N1 -> "${getText(R.string.B2Lvl)} - ${getText(R.string.examN1)}"
            Tests.EXAM_B2_N2 -> "${getText(R.string.B2Lvl)} - ${getText(R.string.examN2)}"
            else -> "${getText(R.string.B2Lvl)} - ${getText(R.string.examN3)}"
        }

        binding.lvlAndExam.text = examName
    }

    private fun initButtonDetails() {
        val premiumUser = User.getPremiumStatus(requireContext())
        var userEarnedReward = false
        fun showDetails(){
            if (binding.rightAnswered.isVisible) {
                binding.listOfIncorrectAnswers.visibility = View.GONE
                binding.detailsSubTitle.visibility = View.GONE
                binding.rightAnswered.visibility = View.GONE
                binding.ivExpandMore.rotation = 360f
            } else {
                binding.rightAnswered.visibility = View.VISIBLE
                binding.ivExpandMore.rotation = 180f
                // If there aren't incorrect answers
                if (mIncorrectAnswersArray.isEmpty()) {
                    binding.listOfIncorrectAnswers.visibility = View.GONE
                    binding.detailsSubTitle.visibility = View.GONE
                } else {
                    binding.listOfIncorrectAnswers.visibility = View.VISIBLE
                    binding.detailsSubTitle.visibility = View.VISIBLE
                }
            }
        }
        binding.btnDetails.setOnClickListener {
            if (premiumUser || userEarnedReward) {
                showDetails()
            } else {
                val onUserEarnedRewardListener = OnUserEarnedRewardListener {
                    userEarnedReward = true
                    showDetails()
                }
                val dialog = Dialogs.dialogSemiRestrictedContent(
                    requireContext(),
                    requireActivity(),
                    onUserEarnedRewardListener
                )
                dialog.show()
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun initTestDetails() {
        binding.rightAnswered.text =
            getString(R.string.correctAnswered) + " $mCorrectAnswers / $mTotalQuestions"
        binding.detailsSubTitle.visibility = View.GONE
        binding.listOfIncorrectAnswers.visibility = View.GONE
        binding.rightAnswered.visibility = View.GONE

        // Convert Array into String
        if (mIncorrectAnswersArray.isNotEmpty()) {
            binding.listOfIncorrectAnswers.text = mIncorrectAnswersArray.joinToString(
                separator = "\n"
            )
        }
    }

    private fun clearOldData() {
        TestsAdapter.incorrectAnswersArray.clear()
        TestsAdapter.correctAnswers = 0
    }

    private fun setOnBackPressedCallback() {
        mCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
                findNavController().popBackStack()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }

    override fun onResume() {
        super.onResume()
        setOnBackPressedCallback()
    }

    override fun onPause() {
        super.onPause()
        clearOldData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCallback.remove()
    }
}