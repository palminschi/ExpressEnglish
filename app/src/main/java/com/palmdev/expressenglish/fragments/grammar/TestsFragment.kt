package com.palmdev.expressenglish.fragments.grammar

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.TestsAdapter
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.Tests
import com.palmdev.expressenglish.databinding.FragmentTestCommonBinding
import com.palmdev.expressenglish.models.ExerciseN2
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class TestsFragment : Fragment(R.layout.fragment_test_common) {

    private lateinit var binding: FragmentTestCommonBinding
    private val mAdapter = TestsAdapter()
    private lateinit var mExamOrQuickTest: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestCommonBinding.bind(view)

        // Set Content
        mExamOrQuickTest = requireArguments().getString(Tests.EXAM_OR_QUICK_TEST, "")
        setTitle()
        initRecyclerView()
        val exercises = getNeededTest()
        mAdapter.setExercises(exercises)
        initPagination()
        initButtons()

    }


    private fun initRecyclerView() {
        binding.recView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recView.setItemViewCacheSize(50)
        binding.recView.adapter = mAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recView)

        /*binding.recView.findViewById<LinearLayout>(R.id.choice1).setOnClickListener {
            Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show()
        }*/
        mAdapter.setOnAnswerSelectedListener {
            val currentPosition = binding.recView.getCurrentPosition()
            val maxPosition = mAdapter.itemCount - 1
            if (currentPosition != maxPosition) {
                Timer(false).schedule(250) {
                    binding.recView.smoothScrollToPosition(currentPosition + 1)
                }
            }

        }
    }

    private fun getNeededTest(): ArrayList<ExerciseN2> {
        val exercisesArray = ArrayList<ExerciseN2>()
        when (mExamOrQuickTest) {
            Tests.QUICK_TEST -> {
                val testsCounter = SharedPref.get(SharedPref.QUICK_TEST_COUNTER, 0)
                when (testsCounter) {
                    0 -> exercisesArray.addAll(Tests.getTest(requireContext(), Tests.QUICK_TEST_N1))
                    1 -> exercisesArray.addAll(Tests.getTest(requireContext(), Tests.QUICK_TEST_N2))
                    else ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.QUICK_TEST_N3))
                }
            }
            Tests.EXAM -> {
                // TODO
                val exam = requireArguments().getString(Tests.EXAM)
                when (exam) {
                    Tests.EXAM_A1_N1 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_A1_N1))
                    Tests.EXAM_A1_N2 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_A1_N2))
                    Tests.EXAM_A1_N3 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_A1_N3))
                    Tests.EXAM_A2_N1 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_A2_N1))
                    Tests.EXAM_A2_N2 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_A2_N2))
                    Tests.EXAM_A2_N3 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_A2_N3))
                    Tests.EXAM_B1_N1 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_B1_N1))
                    Tests.EXAM_B1_N2 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_B1_N2))
                    Tests.EXAM_B1_N3 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_B1_N3))
                    Tests.EXAM_B2_N1 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_B2_N1))
                    Tests.EXAM_B2_N2 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_B2_N2))
                    Tests.EXAM_B2_N3 ->
                        exercisesArray.addAll(Tests.getTest(requireContext(), Tests.EXAM_B2_N3))
                }
            }
        }


        return exercisesArray
    }

    private fun initButtons() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        binding.btnDone.setOnClickListener {
            val correctAnswers = mAdapter.getCorrectAnswers()
            val totalQuestions = mAdapter.itemCount
            val incorrectAnswersArray = mAdapter.getIncorrectAnswersArray()

            if (mExamOrQuickTest == Tests.QUICK_TEST) {
                findNavController().navigate(
                    R.id.action_testsFragment_to_resultQuickTestFragment,
                    bundleOf(
                        CORRECT_ANSWERS to correctAnswers,
                        INCORRECT_ANSWERS_ARRAY to incorrectAnswersArray,
                        TOTAL_QUESTIONS to totalQuestions
                    )
                )

                // update Quick Tests counter
                if (mExamOrQuickTest == Tests.QUICK_TEST) {
                    var testsCounter = SharedPref.get(SharedPref.QUICK_TEST_COUNTER, 0)
                    testsCounter++
                    if (testsCounter >= 3) testsCounter = 0
                    SharedPref.put(SharedPref.QUICK_TEST_COUNTER, testsCounter)
                }
            } else {
                val examID = requireArguments().getString(Tests.EXAM)
                val examLevel = requireArguments().getString(Tests.LEVEL)
                findNavController().navigate(
                    R.id.action_testsFragment_to_resultExamFragment,
                    bundleOf(
                        Tests.EXAM to examID,
                        Tests.LEVEL to examLevel,
                        CORRECT_ANSWERS to correctAnswers,
                        INCORRECT_ANSWERS_ARRAY to incorrectAnswersArray,
                        TOTAL_QUESTIONS to totalQuestions
                    )
                )
            }
        }
    }

    private fun setTitle() {
        if (mExamOrQuickTest == Tests.QUICK_TEST)
            binding.title.text = getString(R.string.quickTest)
        else {
            binding.title.text = getString(R.string.exam)
        }
    }

    private fun RecyclerView?.getCurrentPosition(): Int {
        return (this?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }

    private fun initPagination() {
        var currentPosition = binding.recView.getCurrentPosition() + 1
        val maxPosition = mAdapter.itemCount
        binding.currentExercise.text = currentPosition.toString()
        binding.progressBar.progress = currentPosition
        binding.maxExercises.text = maxPosition.toString()
        binding.progressBar.max = maxPosition

        binding.recView.setOnScrollChangeListener { _, _, _, _, _ ->
            currentPosition = binding.recView.getCurrentPosition() + 1
            binding.currentExercise.text = currentPosition.toString()
            binding.progressBar.progress = currentPosition
            when (currentPosition) {
                1 -> binding.partOfLeftCard.visibility = View.GONE
                in 2 until maxPosition -> {
                    binding.partOfLeftCard.visibility = View.VISIBLE
                    binding.partOfRightCard.visibility = View.VISIBLE
                }
                maxPosition -> binding.partOfRightCard.visibility = View.GONE
            }
        }
    }


    override fun onResume() {
        super.onResume()
        // Hide Bottom Navigation
        MainActivity.bottomNavigationView.visibility = View.GONE
        activity?.window?.navigationBarColor = Color.BLACK
    }

    override fun onStop() {
        super.onStop()
        activity?.window?.navigationBarColor = resources.getColor(R.color.gray_03)
        MainActivity.bottomNavigationView.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter.resetCorrectAnswers()
    }

    companion object {
        const val CORRECT_ANSWERS = "CORRECT_ANSWERS"
        const val TOTAL_QUESTIONS = "TOTAL_QUESTIONS"
        const val INCORRECT_ANSWERS_ARRAY = "INCORRECT_ANSWERS_ARRAY"
    }
}