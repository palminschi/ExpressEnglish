package com.palmdev.expressenglish.fragments.grammar

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.adapters.PracticeAdapter
import com.palmdev.expressenglish.data.Practices
import com.palmdev.expressenglish.databinding.FragmentTestCommonBinding


class PracticeFragment : Fragment(R.layout.fragment_test_common) {

    private lateinit var binding: FragmentTestCommonBinding
    private val mAdapter = PracticeAdapter()
    private lateinit var mLessonID: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestCommonBinding.bind(view)

        initRecyclerView()

        // Set Content
        val lessonTitle = requireArguments().getString(LessonFragment.LESSON_TITLE)!!
        binding.title.text = lessonTitle

        mLessonID = requireArguments().getString(SelectLessonFragment.SELECTED_LESSON)!!
        val exercisesArray = Practices.getExercisesArray(requireContext(), mLessonID)
        mAdapter.setExercises(exercisesArray)


        initPagination()
        initButtons()
    }

    private fun RecyclerView?.getCurrentPosition() : Int {
        return (this?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }

    private fun initPagination() {
        var currentPosition = binding.recView.getCurrentPosition() + 1
        val maxPosition = mAdapter.itemCount
        binding.currentExercise.text = currentPosition.toString()
        binding.progressBar.progress = currentPosition
        binding.maxExercises.text = maxPosition.toString()
        binding.progressBar.max = maxPosition

        binding.recView.setOnScrollChangeListener { v, _, _, _, _ ->
            currentPosition = binding.recView.getCurrentPosition() + 1
            binding.currentExercise.text = currentPosition.toString()
            binding.progressBar.progress = currentPosition
            when (currentPosition){
                1 ->  binding.partOfLeftCard.visibility = View.GONE
                in 2 until maxPosition -> {
                    binding.partOfLeftCard.visibility = View.VISIBLE
                    binding.partOfRightCard.visibility = View.VISIBLE
                }
                maxPosition -> binding.partOfRightCard.visibility = View.GONE
            }
        }
    }

    private fun initButtons() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        binding.btnDone.setOnClickListener {
            val rightAnswers = mAdapter.getCorrectAnswers()
            val totalQuestions = mAdapter.itemCount
            val dialogEnd = Dialogs.dialogEndPractice(requireContext(),
                "$rightAnswers / $totalQuestions")
            dialogEnd.show()
        }
    }

    private fun initRecyclerView(){
        binding.recView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recView.setItemViewCacheSize(30)
        binding.recView.adapter = mAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recView)
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


}