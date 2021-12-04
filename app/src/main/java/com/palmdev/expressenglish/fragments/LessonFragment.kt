package com.palmdev.expressenglish.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Lessons
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentLessonBinding


class LessonFragment : Fragment(R.layout.fragment_lesson) {

    private lateinit var binding: FragmentLessonBinding
    private lateinit var mSelectedLesson: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLessonBinding.bind(view)

        // Get Selected Lesson
        mSelectedLesson = requireArguments().getString(SelectLessonFragment.SELECTED_LESSON)!!
        setTitle(mSelectedLesson)
        // Set Lesson Content
        Lessons.setLessonView(mSelectedLesson, binding.lessonContent)
        // Set Lesson Status
        val lessonStatus = SharedPref.get(mSelectedLesson, Lessons.STATUS_READ)
        if (lessonStatus == Lessons.STATUS_LEARNED) {
            binding.checkBox.isChecked = true
        }
        // Lesson with practice or not
        val withPractice = requireArguments().getBoolean(WITH_PRACTICE)
        if (!withPractice) binding.btnPractice.visibility = View.GONE

        setButtons()

    }

    private fun setButtons(){
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        binding.btnLearned.setOnClickListener {
            binding.apply {
                checkBox.isChecked = !checkBox.isChecked
            }
        }
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                SharedPref.put(mSelectedLesson, Lessons.STATUS_LEARNED)
                User.addLearnedTopics()
            }
            else {
                SharedPref.put(mSelectedLesson, Lessons.STATUS_READ)
                User.removeLearnedTopics()
            }
        }

        binding.btnPractice.setOnClickListener {
            findNavController().navigate(
                R.id.action_lessonFragment_to_practiceFragment,
                bundleOf(
                    SelectLessonFragment.SELECTED_LESSON to mSelectedLesson,
                    LESSON_TITLE to binding.title.text
                )
            )
        }
    }

    private fun setTitle(lessonNumber: String){
        when (lessonNumber) {
            Lessons.LESSON_1_01 -> binding.title.text = getString(R.string.lesson101_title_short)
            Lessons.LESSON_1_02 -> binding.title.text = getString(R.string.lesson102_title_short)
            Lessons.LESSON_1_03 -> binding.title.text = getString(R.string.lesson103_title_short)
            Lessons.LESSON_1_04 -> binding.title.text = getString(R.string.lesson104_title_short)
            Lessons.LESSON_1_05 -> binding.title.text = getString(R.string.lesson105_title_short)
            Lessons.LESSON_1_06 -> binding.title.text = getString(R.string.lesson106_title_short)
            Lessons.LESSON_1_07 -> binding.title.text = getString(R.string.lesson107_title_short)
            Lessons.LESSON_1_08 -> binding.title.text = getString(R.string.lesson108_title_short)
            Lessons.LESSON_1_09 -> binding.title.text = getString(R.string.lesson109_title_short)
            Lessons.LESSON_1_10 -> binding.title.text = getString(R.string.lesson110_title_short)
            Lessons.LESSON_1_11 -> binding.title.text = getString(R.string.lesson111_title_short)
            Lessons.LESSON_1_12 -> binding.title.text = getString(R.string.lesson112_title_short)
            Lessons.LESSON_1_13 -> binding.title.text = getString(R.string.lesson113_title_short)
            Lessons.LESSON_1_14 -> binding.title.text = getString(R.string.lesson114_title_short)
            Lessons.LESSON_1_15 -> binding.title.text = getString(R.string.lesson115_title_short)
            Lessons.LESSON_1_16 -> binding.title.text = getString(R.string.lesson116_title_short)
            Lessons.LESSON_1_17 -> binding.title.text = getString(R.string.lesson117_title_short)
            Lessons.LESSON_1_18 -> binding.title.text = getString(R.string.lesson118_title_short)
            Lessons.LESSON_1_19 -> binding.title.text = getString(R.string.lesson119_title_short)
        }
    }

    companion object{
        const val LESSON_TITLE = "LESSON_TITLE"
        const val WITH_PRACTICE = "WITH_PRACTICE"
    }
}